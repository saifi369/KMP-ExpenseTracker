package presentation.screen.addexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.local.database.ExpenseDatabase
import data.local.model.TransactionEntity
import kotlinx.coroutines.launch

class AddExpenseVM(
    private val expenseDatabase: ExpenseDatabase,
) : ViewModel() {

    fun saveExpense(
        transactionEntity: TransactionEntity,
    ) {
        viewModelScope.launch {
            expenseDatabase.transactionDao().upsertExpense(transactionEntity)
        }
    }
}