package data.local.mappers

import data.local.model.WalletEntity
import domain.model.Wallet

fun WalletEntity.mapToDomainModel() = Wallet(
    walletId = walletId,
    balance = this.balance,
    name = name
)

fun Wallet.mapToDataModel() = WalletEntity(
    walletId = walletId,
    name = name,
    balance = balance
)

fun List<WalletEntity>.mapToDomainModel(): List<Wallet> {
    return this.map {
        it.mapToDomainModel()
    }
}