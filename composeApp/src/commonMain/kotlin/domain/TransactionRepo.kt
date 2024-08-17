package domain

import domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepo {
    suspend fun addTransaction(transaction: Transaction)
    suspend fun getTransactions(): Flow<List<Transaction>>
}