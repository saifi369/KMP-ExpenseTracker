package data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.local.model.TransactionEntity
import data.local.model.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertExpense(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM `transaction` WHERE walletId =:walletId")
    fun getTransactionForWallet(walletId: Int): Flow<List<TransactionEntity>>

    @Query("SELECT SUM(amount) FROM `transaction` WHERE walletID =:walletId AND transactionType =:type")
    fun getTotalTransactionSum(walletId: Int, type: TransactionType): Flow<Double>

}