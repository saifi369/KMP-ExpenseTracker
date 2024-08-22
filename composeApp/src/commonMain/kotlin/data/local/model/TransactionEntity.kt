package data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transaction",
    foreignKeys = [
        ForeignKey(
            entity = WalletEntity::class,
            parentColumns = ["walletId"],
            childColumns = ["walletId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["walletId"])]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val transactionId: Int = 0,
    val walletId: Int,
    val title: String,
    val date: String,
    val amount: Double,
    val category: String,
    val message: String,
    val transactionType: TransactionType,
)

enum class TransactionType {
    Income,
    Expense
}