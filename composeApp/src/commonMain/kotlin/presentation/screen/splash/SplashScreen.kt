package presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.screen.navigation.SplashNavRoutes
import presentation.screen.onboarding.SplashScreenVM
import presentation.theme.AppColor

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigate: (SplashNavRoutes) -> Unit
) {
    val viewModel = koinViewModel<SplashScreenVM>()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isOnBoarded by viewModel.isOnboarded.collectAsStateWithLifecycle()

    if (!isLoading) {
        LaunchedEffect(Unit) {
            if (isOnBoarded) onNavigate(SplashNavRoutes.App) else onNavigate(SplashNavRoutes.OnBoarding)
        }
    }

    Box(
        modifier = modifier.fillMaxSize().background(AppColor.mainGreen),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(Res.drawable.ic_logo),
            null
        )
    }
}