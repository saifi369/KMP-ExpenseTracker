package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.KoinContext
import ui.bottomnavigation.BottomNavigationBar
import ui.screen.addexpense.AddExpenseScreen
import ui.screen.floatingactionbutton.MainFab
import ui.screen.home.HomeScreenNavHost
import ui.screen.navigation.NavGraphs
import ui.theme.AppColor

@Composable
fun AppContent() {

    KoinContext {
        val rootNavController = rememberNavController()
        val keyboardController = LocalSoftwareKeyboardController.current
        val currentNavBackStackEntry by rootNavController.currentBackStackEntryAsState()
        val isHomeScreen = currentNavBackStackEntry?.destination?.hierarchy?.any {
            it.hasRoute(NavGraphs.Home::class) ||
                    it.hasRoute(NavGraphs.Transactions::class) ||
                    it.hasRoute(NavGraphs.Category::class) ||
                    it.hasRoute(NavGraphs.Profile::class)
        } == true

        Surface(
            modifier = Modifier
                .background(AppColor.mainGreen)
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            color = AppColor.mainGreen
        ) {
            Scaffold(
                floatingActionButton = {
                    if (isHomeScreen) {
                        MainFab {
                            rootNavController.navigate(NavGraphs.AddExpense(it))
                        }
                    }
                },
                bottomBar = {
                    if (isHomeScreen) {
                        BottomNavigationBar(rootNavController)
                    }
                }
            ) { innerPadding ->

                NavHost(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    navController = rootNavController,
                    startDestination = NavGraphs.Home
                ) {
                    composable<NavGraphs.Home> {
                        HomeScreenNavHost()
                    }
                    composable<NavGraphs.AddExpense> {
                        val args = it.toRoute<NavGraphs.AddExpense>()
                        AddExpenseScreen(args.transactionType) {
                            keyboardController?.hide()
                            rootNavController.popBackStack()
                        }
                    }
                    composable<NavGraphs.Transactions> {
                    }
                    composable<NavGraphs.Category> {
                    }
                    composable<NavGraphs.Profile> {
                    }
                }
            }
        }
    }
}