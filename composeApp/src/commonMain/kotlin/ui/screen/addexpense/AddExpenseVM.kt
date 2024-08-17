package ui.screen.addexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.local.database.ExpenseDatabase
import data.local.database.model.ExpenseEntity
import kotlinx.coroutines.launch

class AddExpenseVM(
    private val expenseDatabase: ExpenseDatabase,
) : ViewModel() {

    fun saveExpense(
        expenseEntity: ExpenseEntity,
    ) {
        viewModelScope.launch {
            expenseDatabase.expenseDao().upsertExpense(expenseEntity)
        }
    }
}