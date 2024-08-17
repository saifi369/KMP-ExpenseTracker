package ui.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class SplashNavRoutes {
    @Serializable
    data object App : SplashNavRoutes()

    @Serializable
    data object OnBoarding : SplashNavRoutes()

    @Serializable
    data object Splash : SplashNavRoutes()
}