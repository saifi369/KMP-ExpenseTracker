package domain.repo

import domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepo {
    suspend fun addTransaction(transaction: Transaction)
    suspend fun getTransactionsForWallet(walletId: Int): Flow<List<Transaction>>
}