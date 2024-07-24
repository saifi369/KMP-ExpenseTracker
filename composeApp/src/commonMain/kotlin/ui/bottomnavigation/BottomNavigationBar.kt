package ui.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.home_tab_title
import expensify.composeapp.generated.resources.settings_tab_title
import expensify.composeapp.generated.resources.transaction_tab_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavigationBar() {
    val bottomBarItems = listOf(
        BottomNavigationItem(
            title = Res.string.home_tab_title,
            Icons.Filled.Home,
            Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = Res.string.transaction_tab_title,
            Icons.AutoMirrored.Filled.List,
            Icons.AutoMirrored.Filled.List
        ),
        BottomNavigationItem(
            title = Res.string.settings_tab_title,
            Icons.Filled.Settings,
            Icons.Outlined.Settings
        )
    )

    BottomAppBar {
        bottomBarItems.forEach {
            NavigationBarItem(
                false,
                onClick = {},
                label = { Text(stringResource(it.title)) },
                icon = {
                    Icon(it.selectedIcon, null)
                }
            )
        }
    }
}
