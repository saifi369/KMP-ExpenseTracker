package domain.repo

import domain.model.Wallet
import domain.utils.Error
import domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface WalletRepo {
    suspend fun insertWallet(wallet: Wallet)
    suspend fun getWallets(): Flow<Result<List<Wallet>, Error>>
}