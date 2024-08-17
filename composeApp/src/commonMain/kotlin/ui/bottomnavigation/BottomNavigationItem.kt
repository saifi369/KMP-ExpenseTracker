package ui.bottomnavigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import ui.screen.navigation.NavGraphs

data class BottomNavigationItem(
    val title: StringResource,
    val selectedIcon: DrawableResource,
    val navRoute : NavGraphs
)
