package data.local.repoimpl

import data.local.database.dao.TransactionDao
import data.local.mappers.mapToDataModel
import data.local.mappers.mapToDomainModel
import domain.repo.TransactionRepo
import domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepoImpl(private val transactionDao: TransactionDao) : TransactionRepo {
    override suspend fun addTransaction(transaction: Transaction) {
        transactionDao.upsertExpense(transaction.mapToDataModel())
    }

    override suspend fun getTransactionsForWallet(walletId: Int): Flow<List<Transaction>> {
        return transactionDao.getTransactionForWallet(walletId).map {
            it.mapToDomainModel()
        }
    }
}