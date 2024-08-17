package ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.local.database.ExpenseDatabase
import data.local.database.model.ExpenseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenVM(
    private val expenseDatabase: ExpenseDatabase,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _expensesList = MutableStateFlow(emptyList<ExpenseEntity>())
    val expensesList = _expensesList.asStateFlow()

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        _isLoading.value = true
        viewModelScope.launch {
            expenseDatabase.expenseDao().getAllExpenses().collectLatest {
                _expensesList.value = it
                _isLoading.value = false
            }
        }
    }

}