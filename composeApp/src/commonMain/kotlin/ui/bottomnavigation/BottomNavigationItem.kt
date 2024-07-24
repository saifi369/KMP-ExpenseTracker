package ui.bottomnavigation

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
data class BottomNavigationItem(
    val title: StringResource,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
