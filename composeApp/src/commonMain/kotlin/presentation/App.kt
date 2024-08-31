package presentation

import AllSetScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.screen.AppContent
import presentation.screen.accountsetup.AccountSetupScreen
import presentation.screen.accountsetup.AccountSetupWelcomeScreen
import presentation.screen.navigation.SplashNavRoutes
import presentation.screen.onboarding.GetStartedScreen
import presentation.screen.splash.SplashScreen
import presentation.theme.AppTheme

@Composable
@Preview
fun App() {
  AppTheme {
    KoinContext {
      val splashNavController = rememberNavController()

      NavHost(splashNavController, SplashNavRoutes.Splash) {
        composable<SplashNavRoutes.Splash> {
          SplashScreen {
            splashNavController.popBackStack()
            splashNavController.navigate(it)
          }
        }
        composable<SplashNavRoutes.OnBoarding> {
          GetStartedScreen {
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
              splashNavController.navigate(SplashNavRoutes.AllSet) {
                popUpTo<SplashNavRoutes.OnBoarding> {
                  inclusive = true
                }
              }
            },
            onBackButtonClick = {
              splashNavController.popBackStack()
            }
          )
        }
        composable<SplashNavRoutes.AllSet> {
          AllSetScreen {
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