package data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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