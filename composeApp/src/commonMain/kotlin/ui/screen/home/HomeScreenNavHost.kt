package ui.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.screen.navigation.HomeNavRoutes

@Composable
fun HomeScreenNavHost() {
    val homeNavController = rememberNavController()

    NavHost(
        navController = homeNavController,
        startDestination = HomeNavRoutes.Home
    ) {
        addHomeScreen()
    }
}

private fun NavGraphBuilder.addHomeScreen() {
    composable<HomeNavRoutes.Home> {
        HomeScreen()
    }
}