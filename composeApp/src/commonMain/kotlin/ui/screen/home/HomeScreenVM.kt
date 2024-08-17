package ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.TransactionRepo
import domain.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenVM(
    private val transactionRepo: TransactionRepo,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _expensesList = MutableStateFlow(emptyList<Transaction>())
    val expensesList = _expensesList.asStateFlow()

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        _isLoading.value = true
        viewModelScope.launch {
            transactionRepo.getTransactions().collectLatest {
                _expensesList.value = it
                _isLoading.value = false
            }
        }
    }

}