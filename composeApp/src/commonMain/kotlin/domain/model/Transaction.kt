package domain.model

import data.local.model.TransactionType

data class Transaction(
    val title: String,
    val date: String,
    val amount: Double,
    val message: String,
    val category: String,
    val transactionType: TransactionType
)
