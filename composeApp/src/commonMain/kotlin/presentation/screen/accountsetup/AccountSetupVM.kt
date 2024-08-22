package presentation.screen.accountsetup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.User
import domain.usecase.CreateWalletUseCase
import domain.usecase.SaveUserUseCase
import domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountSetupVM(
    private val saveUserUserUseCase: SaveUserUseCase,
    private val createWalletUseCase: CreateWalletUseCase
) : ViewModel() {

    private val _isUserSaved = MutableStateFlow(false)
    val isUserSaved = _isUserSaved.asStateFlow()

    fun createUser(username: String, walletName: String, walletBalance: Double) {
        viewModelScope.launch {
            val user = User(name = username)
            when (saveUserUserUseCase(user)) {
                is Result.Success -> {
                    createUserWallet(walletName, walletBalance)
                }
                is Result.Error -> {
                    _isUserSaved.value = false
                }
            }
        }
    }

    private fun createUserWallet(walletName: String, walletBalance: Double) {
        viewModelScope.launch {
            createWalletUseCase.invoke(walletName, walletBalance)
            _isUserSaved.value = true
        }
    }
}