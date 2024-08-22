package data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.database.dao.TransactionDao
import data.local.database.dao.UserDao
import data.local.database.dao.WalletDao
import data.local.model.TransactionEntity
import data.local.model.UserEntity
import data.local.model.WalletEntity

@Database(
    entities = [TransactionEntity::class, WalletEntity::class, UserEntity::class],
    version = 1
)
//@ConstructedBy(ExpenseDatabaseConstructor::class)
abstract class ExpenseDatabase : RoomDatabase(), DB {

    abstract fun transactionDao(): TransactionDao
    abstract fun userDao(): UserDao
    abstract fun walletDao(): WalletDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

//expect object ExpenseDatabaseConstructor : RoomDatabaseConstructor<ExpenseDatabase>

interface DB {
    fun clearAllTables() {}
}