package presentation.screen.addexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.Transaction
import domain.model.TransactionType
import domain.model.Wallet
import domain.repo.TransactionRepo
import domain.usecase.GetWalletUseCase
import domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AddExpenseVM(
    private val getWalletUseCase: GetWalletUseCase,
    private val transactionRepo: TransactionRepo
) : ViewModel() {

    private val _walletsList = MutableStateFlow(emptyList<Wallet>())
    val walletsList = _walletsList.asStateFlow()

    init {
        loadWallets()
    }

    fun saveExpense(
        title: String,
        date: String,
        amount: Double,
        category: String,
        message: String,
        transactionType: String,
        wallet: Wallet,
    ) {
        viewModelScope.launch {

            val transaction = Transaction(
                title = title,
                date = date,
                amount = amount,
                category = category,
                message = message,
                walletId = wallet.walletId,
                transactionType = if (transactionType == TransactionType.INCOME.title) TransactionType.INCOME else TransactionType.EXPENSE
            )

            transactionRepo.addTransaction(transaction)
        }
    }

    private fun loadWallets() {
        viewModelScope.launch {
            getWalletUseCase().collectLatest {
                when (it) {
                    is Result.Error -> {}
                    is Result.Success -> {
                        _walletsList.value = it.data
                    }
                }
            }
        }
    }
}