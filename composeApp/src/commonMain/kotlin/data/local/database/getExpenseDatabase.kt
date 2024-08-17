package data.local.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

//fun getExpenseDatabase(
//    builder: RoomDatabase.Builder<ExpenseDatabase>,
//): ExpenseDatabase {
//
//    return builder
//        .fallbackToDestructiveMigration(dropAllTables = true)
//        .setDriver(BundledSQLiteDriver())
//        .setQueryCoroutineContext(Dispatchers.IO)
//        .build()
//}