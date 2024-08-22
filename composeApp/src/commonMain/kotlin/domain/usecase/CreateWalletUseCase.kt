package domain.usecase

import domain.model.Wallet
import domain.repo.WalletRepo

class CreateWalletUseCase(
    private val walletRepo: WalletRepo
) {
    suspend operator fun invoke(walletName: String, walletBalance: Double) {
        walletRepo.insertWallet(Wallet(name = walletName, balance = walletBalance))
    }
}