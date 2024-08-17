package ui.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class HomeNavRoutes {
    @Serializable
    data object Home : HomeNavRoutes()
}