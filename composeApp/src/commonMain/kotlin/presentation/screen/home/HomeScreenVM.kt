package presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.Transaction
import domain.model.User
import domain.model.Wallet
import domain.repo.TransactionRepo
import domain.usecase.GetUserUseCase
import domain.usecase.GetWalletUseCase
import domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenVM(
    private val transactionRepo: TransactionRepo,
    private val getWalletUseCase: GetWalletUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _expensesList = MutableStateFlow(emptyList<Transaction>())
    val expensesList = _expensesList.asStateFlow()

    private val _walletsList = MutableStateFlow(emptyList<Wallet>())
    val walletsList = _walletsList.asStateFlow()

    private val _user = MutableStateFlow<User?>(null)
    val userInfo = _user.asStateFlow()

    init {
        loadUserInfo()
        loadWallets()
    }

    private fun loadUserInfo() {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = getUserUseCase()) {
                is Result.Success -> {
                    _user.value = result.data
                }

                is Result.Error -> {
                    _isLoading.value = false
                }
            }
        }
    }

    private fun loadWallets() {
        _isLoading.value = true
        viewModelScope.launch {
            getWalletUseCase().collectLatest {
                when (it) {
                    is Result.Error -> {
                        _isLoading.value = false
                    }

                    is Result.Success -> {
                        _walletsList.value = it.data
                        loadExpenses()
                    }
                }
            }
        }
    }

    private fun loadExpenses() {
        _isLoading.value = true
        viewModelScope.launch {
            val wallet = _walletsList.value.firstOrNull()
            if (wallet != null) {
                transactionRepo.getTransactionsForWallet(wallet.walletId).collectLatest {
                    _expensesList.value = it
                }
            }
            _isLoading.value = false
        }
    }
}