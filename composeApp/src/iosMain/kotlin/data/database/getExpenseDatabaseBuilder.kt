package data.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.local.database.ExpenseDatabase
import data.local.database.instantiateImpl
import data.utils.getDocumentsDirectory

fun getExpenseDatabaseBuilder(): ExpenseDatabase {

    val dbFile = getDocumentsDirectory() + "/expense.db"

    return Room.databaseBuilder<ExpenseDatabase>(
        name = dbFile,
        factory = { ExpenseDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}