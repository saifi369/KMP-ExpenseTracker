package data.local

import data.local.database.ExpenseDatabase
import data.local.model.mapToDataModel
import data.local.model.mapToDomainModel
import domain.TransactionRepo
import domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepoImpl(private val expenseDatabase: ExpenseDatabase) : TransactionRepo {
    override suspend fun addTransaction(transaction: Transaction) {
        expenseDatabase.expenseDao().upsertExpense(transaction.mapToDataModel())
    }

    override suspend fun getTransactions(): Flow<List<Transaction>> {
        return expenseDatabase.expenseDao().getAllExpenses().map {
            it.mapToDomainModel()
        }
    }
}