package data.local.repoimpl

import data.local.database.dao.WalletDao
import data.local.mappers.mapToDataModel
import data.local.mappers.mapToDomainModel
import domain.model.Wallet
import domain.repo.WalletRepo
import domain.utils.Error
import domain.utils.Result
import domain.utils.WalletError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class WalletRepoImpl(
    private val walletDao: WalletDao
) : WalletRepo {
    override suspend fun insertWallet(wallet: Wallet) {
        val walletEntity = wallet.mapToDataModel()
        walletDao.upsertWallet(walletEntity)
    }

    override suspend fun getWallets(): Flow<Result<List<Wallet>, Error>> {
        return try {
            val wallets = walletDao.getWallets().map {
                it.mapToDomainModel()
            }.map {
                if (it.isEmpty()) {
                    Result.Error<List<Wallet>, Error>(WalletError.NO_WALLET_FOUND)
                }
                Result.Success<List<Wallet>, Error>(it)
            }

            wallets

        } catch (e: Exception) {
            flow {
                Result.Error<List<Wallet>, Error>(WalletError.NO_WALLET_FOUND)
            }
        }
    }
}