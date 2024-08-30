package presentation.screen.accountsetup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.User
import domain.repo.UserPrefRepo
import domain.usecase.CreateWalletUseCase
import domain.usecase.SaveUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AccountSetupVM(
    private val saveUserUserUseCase: SaveUserUseCase,
    private val createWalletUseCase: CreateWalletUseCase,
    private val userPrefRepo: UserPrefRepo,
) : ViewModel() {

    private val _isUserSaved = MutableSharedFlow<Boolean>()
    val isUserSaved = _isUserSaved.asSharedFlow()

    fun createUser(username: String, walletName: String, walletBalance: Double) {
        viewModelScope.launch {
            val user = User(name = username)
            when (saveUserUserUseCase(user)) {
                is domain.utils.Result.Success -> {
                    createUserWallet(walletName, walletBalance)
                }

                is domain.utils.Result.Error -> {
                    _isUserSaved.emit(false)
                }
            }
        }
    }

    private fun createUserWallet(walletName: String, walletBalance: Double) {
        viewModelScope.launch {
            createWalletUseCase.invoke(walletName, walletBalance)
            setOnboardedStatus(onBoarded = true)
            _isUserSaved.emit(true)
        }
    }

    private suspend fun setOnboardedStatus(onBoarded: Boolean) {
        userPrefRepo.saveValue(onBoarded)
    }
}