package presentation.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.repo.UserPrefRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SplashScreenVM(
    private val userPrefRepo: UserPrefRepo,
) : ViewModel() {

    private val _isOnboarded = MutableStateFlow(false)
    val isOnboarded = _isOnboarded

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading

    init {
        checkOnBoardingStatus()
    }

    private fun checkOnBoardingStatus() {
        viewModelScope.launch {
            _isLoading.value = true
            userPrefRepo.readValue().collectLatest {
                _isOnboarded.value = it
                _isLoading.value = false
            }
        }
    }

    fun setOnboardedStatus(onBoarded: Boolean) {
        viewModelScope.launch {
            userPrefRepo.saveValue(onBoarded)
        }
    }
}