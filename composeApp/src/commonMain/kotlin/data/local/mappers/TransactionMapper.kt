package data.local.mappers

import data.local.model.TransactionEntity
import domain.model.Transaction
import domain.model.TransactionType


fun Transaction.mapToDomainModel() = Transaction(
    title = title,
    date = date,
    amount = amount,
    category = category,
    message = message,
    transactionType = transactionType,
    walletId = walletId,
    transactionId = transactionId
)

fun List<TransactionEntity>.mapToDomainModel(): List<Transaction> {
    return this.map { it.mapToDomainModel() }
}

fun Transaction.mapToDataModel() = TransactionEntity(
    title = title,
    date = date,
    amount = amount,
    category = category,
    message = message,
    transactionType = transactionType.mapToDataModel(),
    walletId = walletId,
    transactionId = transactionId
)

fun TransactionEntity.mapToDomainModel() = Transaction(
    title = title,
    date = date,
    amount = amount,
    category = category,
    message = message,
    transactionType = transactionType.mapToDomainModel(),
    walletId = walletId,
    transactionId = transactionId
)

fun TransactionType.mapToDataModel(): data.local.model.TransactionType {
    return when (this) {
        TransactionType.INCOME -> data.local.model.TransactionType.Income
        TransactionType.EXPENSE -> data.local.model.TransactionType.Expense
    }
}

fun data.local.model.TransactionType.mapToDomainModel(): TransactionType {
    return when (this) {
        data.local.model.TransactionType.Income -> TransactionType.INCOME
        data.local.model.TransactionType.Expense -> TransactionType.EXPENSE
    }
}