package domain.model

data class Transaction(
    val transactionId: Int = 0,
    val walletId: Int,
    val title: String,
    val date: String,
    val amount: Double,
    val message: String,
    val category: String,
    val transactionType: TransactionType
)

enum class TransactionType(val title: String) {
    INCOME("Income"),
    EXPENSE("Expense")
}