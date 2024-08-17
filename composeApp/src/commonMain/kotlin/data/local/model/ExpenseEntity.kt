package data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import domain.model.Transaction

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val date: String,
    val amount: Double,
    val category: String,
    val message: String,
    val transactionType: TransactionType,
)

enum class TransactionType() {
    Income,
    Expense
}

fun ExpenseEntity.mapToDomainModel() = Transaction(
    title = title,
    date = date,
    amount = amount,
    category = category,
    message = message,
    transactionType = transactionType
)

fun List<ExpenseEntity>.mapToDomainModel(): List<Transaction> {
    return this.map { it.mapToDomainModel() }
}

fun Transaction.mapToDataModel() = ExpenseEntity(
    title = title,
    date = date,
    amount = amount,
    category = category,
    message = message,
    transactionType = transactionType
)