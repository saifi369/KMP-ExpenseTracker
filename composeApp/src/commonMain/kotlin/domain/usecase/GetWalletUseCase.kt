package domain.usecase

import domain.model.Wallet
import domain.repo.WalletRepo
import domain.utils.Error
import domain.utils.Result
import kotlinx.coroutines.flow.Flow

class GetWalletUseCase(
    private val walletRepo: WalletRepo
) {
    suspend operator fun invoke(): Flow<Result<List<Wallet>, Error>> {
        return walletRepo.getWallets()
    }
}