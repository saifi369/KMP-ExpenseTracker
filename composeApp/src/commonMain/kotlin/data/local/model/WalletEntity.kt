package data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet")
data class WalletEntity(
    @PrimaryKey(autoGenerate = true)
    val walletId: Int = 0,
    val name: String,
    val balance: Double
)