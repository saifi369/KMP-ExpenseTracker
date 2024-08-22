package data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.local.model.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWallet(walletEntity: WalletEntity)

    @Delete
    suspend fun deleteWallet(walletEntity: WalletEntity)

    @Query("SELECT * FROM wallet")
    fun getWallets(): Flow<List<WalletEntity>>

}