package presentation.bottomnavigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import presentation.screen.navigation.NavGraphs

data class BottomNavigationItem(
    val title: StringResource,
    val selectedIcon: DrawableResource,
    val navRoute : NavGraphs
)
