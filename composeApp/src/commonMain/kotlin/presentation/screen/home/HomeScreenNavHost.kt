package presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.screen.navigation.HomeNavRoutes

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

@OptIn(KoinExperimentalAPI::class)
private fun NavGraphBuilder.addHomeScreen() {
    composable<HomeNavRoutes.Home> {
        val viewModel: HomeScreenVM = koinViewModel()
        HomeScreen(viewModel)
    }
}