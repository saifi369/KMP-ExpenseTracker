package data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.database.model.ExpenseEntity

@Database(entities = [ExpenseEntity::class], version = 1)
//@ConstructedBy(ExpenseDatabaseConstructor::class)
abstract class ExpenseDatabase : RoomDatabase(), DB {

    abstract fun expenseDao(): ExpenseDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

//expect object ExpenseDatabaseConstructor : RoomDatabaseConstructor<ExpenseDatabase>

interface DB {
    fun clearAllTables() {}
}