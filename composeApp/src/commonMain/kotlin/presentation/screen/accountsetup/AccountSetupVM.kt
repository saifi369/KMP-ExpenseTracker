package presentation.screen.accountsetup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.User
import domain.repo.UserPrefRepo
import domain.usecase.CreateWalletUseCase
import domain.usecase.SaveUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountSetupVM(
    private val saveUserUserUseCase: SaveUserUseCase,
    private val createWalletUseCase: CreateWalletUseCase,
    private val userPrefRepo: UserPrefRepo,
) : ViewModel() {

    private val _isUserSaved = MutableStateFlow(false)
    val isUserSaved = _isUserSaved.asStateFlow()

    fun createUser(username: String, walletName: String, walletBalance: Double) {
        _isUserSaved.value = true
        viewModelScope.launch {
            val user = User(name = username)
            when (saveUserUserUseCase(user)) {
                is domain.utils.Result.Success -> {
                    createUserWallet(walletName, walletBalance)
                }

                is domain.utils.Result.Error -> {
                    _isUserSaved.value = false
                }
            }
        }
    }

    private fun createUserWallet(walletName: String, walletBalance: Double) {
        viewModelScope.launch {
            createWalletUseCase.invoke(walletName, walletBalance)
            setOnboardedStatus(onBoarded = true)
            _isUserSaved.value = true
        }
    }

    private suspend fun setOnboardedStatus(onBoarded: Boolean) {
        userPrefRepo.saveValue(onBoarded)
    }
}