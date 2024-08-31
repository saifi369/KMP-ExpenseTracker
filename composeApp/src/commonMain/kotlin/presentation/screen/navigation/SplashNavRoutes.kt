package presentation.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class SplashNavRoutes {
    @Serializable
    data object App : SplashNavRoutes()

    @Serializable
    data object OnBoarding : SplashNavRoutes()

    @Serializable
    data object Splash : SplashNavRoutes()

    @Serializable
    data object AccountSetupWelcome : SplashNavRoutes()

    @Serializable
    data object AccountSetup : SplashNavRoutes()

    @Serializable
    data object AllSet : SplashNavRoutes()
}