package presentation

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
import presentation.screen.AppContent
import presentation.screen.accountsetup.AccountSetupScreen
import presentation.screen.accountsetup.AccountSetupWelcomeScreen
import presentation.screen.navigation.SplashNavRoutes
import presentation.screen.onboarding.GetStartedScreen
import presentation.screen.onboarding.SplashScreenVM
import presentation.screen.splash.SplashScreen
import presentation.theme.AppTheme

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
                        splashNavController.popBackStack()
                        splashNavController.navigate(SplashNavRoutes.AccountSetupWelcome)
                    }
                }
                composable<SplashNavRoutes.AccountSetupWelcome> {
                    AccountSetupWelcomeScreen {
                        splashNavController.navigate(SplashNavRoutes.AccountSetup)
                    }
                }
                composable<SplashNavRoutes.AccountSetup> {
                    AccountSetupScreen(
                        onDoneClick = {
//                            viewModel.setOnboardedStatus(true)
                            splashNavController.navigate(SplashNavRoutes.App) {
                                popUpTo<SplashNavRoutes.AccountSetupWelcome> {
                                    inclusive = true
                                }
                            }
                        },
                        onBackButtonClick = {
                            splashNavController.popBackStack()
                        }
                    )
                }
                composable<SplashNavRoutes.App> {
                    AppContent()
                }
            }
        }
    }
}