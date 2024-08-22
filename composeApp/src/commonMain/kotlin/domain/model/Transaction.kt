package domain.model

data class Transaction(
    val transactionId: Int,
    val walletId: Int,
    val title: String,
    val date: String,
    val amount: Double,
    val message: String,
    val category: String,
    val transactionType: TransactionType
)

enum class TransactionType {
    INCOME,
    EXPENSE
}