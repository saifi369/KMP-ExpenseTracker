package ui.bottomnavigation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.category_tab_title
import expensify.composeapp.generated.resources.home_tab_title
import expensify.composeapp.generated.resources.ic_category
import expensify.composeapp.generated.resources.ic_home
import expensify.composeapp.generated.resources.ic_profile
import expensify.composeapp.generated.resources.ic_transactions
import expensify.composeapp.generated.resources.profile_tab_title
import expensify.composeapp.generated.resources.transaction_tab_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.screen.navigation.NavGraphs
import ui.theme.AppColor

@Composable
fun BottomNavigationBar(rootNavController: NavHostController) {
    val bottomBarItems = listOf(
        BottomNavigationItem(
            title = Res.string.home_tab_title,
            Res.drawable.ic_home,
            NavGraphs.Home
        ),
        BottomNavigationItem(
            title = Res.string.transaction_tab_title,
            Res.drawable.ic_transactions,
            NavGraphs.Transactions
        ),
        BottomNavigationItem(
            title = Res.string.category_tab_title,
            Res.drawable.ic_category,
            NavGraphs.Category
        ),
        BottomNavigationItem(
            title = Res.string.profile_tab_title,
            Res.drawable.ic_profile,
            NavGraphs.Profile
        )
    )

    val navBackStackEntry = rootNavController.currentBackStackEntryAsState()
//    val shapeColor = if (isSelected) AppColor.backgroundGreen else Color.Transparent

    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
        containerColor = AppColor.mainGreen,
    ) {
        bottomBarItems.forEach { item ->

            val isSelected = navBackStackEntry.value?.destination?.hierarchy?.any {
                it.hasRoute(item.navRoute::class)
            } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    rootNavController.navigate(item.navRoute)
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = AppColor.backgroundGreen
                ),
                label = { Text(stringResource(item.title)) },
                icon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(item.selectedIcon), contentDescription = null
                    )
                }
            )
        }
    }
}