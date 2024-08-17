package data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import data.local.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenseentity")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Insert
    suspend fun upsertExpense(expenseEntity: ExpenseEntity)

}