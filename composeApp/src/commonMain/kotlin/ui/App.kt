package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.screen.AppContent
import ui.screen.navigation.SplashNavRoutes
import ui.screen.onboarding.GetStartedScreen
import ui.screen.onboarding.SplashScreenVM
import ui.screen.splash.SplashScreen
import ui.theme.AppTheme

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    AppTheme {

        KoinContext {
            val splashNavController = rememberNavController()

            val viewModel = koinViewModel<SplashScreenVM>()

            val isLoading by viewModel.isLoading.collectAsState()
            val isOnBoarded by viewModel.isOnboarded.collectAsState()

            if (!isLoading) {
                splashNavController.popBackStack()
                splashNavController.navigate(if (isOnBoarded) SplashNavRoutes.App else SplashNavRoutes.OnBoarding)
            }
            NavHost(splashNavController, SplashNavRoutes.Splash) {
                composable<SplashNavRoutes.Splash> {
                    SplashScreen()
                }
                composable<SplashNavRoutes.OnBoarding> {
                    GetStartedScreen {
                        viewModel.setOnboardedStatus(it)
                        splashNavController.popBackStack()
                        splashNavController.navigate(SplashNavRoutes.App)
                    }
                }
                composable<SplashNavRoutes.App> {
                    AppContent()
                }
            }
        }
    }
}