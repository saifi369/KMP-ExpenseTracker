package di

import data.database.getExpenseDatabaseBuilder
import data.local.createDataStore
import data.local.database.ExpenseDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { createDataStore(null) }
    single<ExpenseDatabase> { getExpenseDatabaseBuilder() }
//    single<RoomDatabase.Builder<ExpenseDatabase>> { getExpenseDatabaseBuilder() }
}