package presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
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
import presentation.bottomnavigation.BottomNavigationBar
import presentation.screen.addexpense.AddExpenseScreen
import presentation.screen.category.CategoryScreen
import presentation.screen.floatingactionbutton.MainFab
import presentation.screen.home.HomeScreenNavHost
import presentation.screen.navigation.NavGraphs
import presentation.theme.AppTheme

@Composable
fun AppContent() {

  val keyboardController = LocalSoftwareKeyboardController.current
  val rootNavController = rememberNavController()
  val currentNavBackStackEntry by rootNavController.currentBackStackEntryAsState()
  val isHomeScreen = currentNavBackStackEntry?.destination?.hierarchy?.any {
    it.hasRoute(NavGraphs.Home::class) ||
            it.hasRoute(NavGraphs.Transactions::class) ||
            it.hasRoute(NavGraphs.Category::class) ||
            it.hasRoute(NavGraphs.Profile::class)
  } == true

  Scaffold(
    modifier = Modifier
      .background(AppTheme.colorScheme.mainGreen)
      .fillMaxSize(),
    floatingActionButton = {
      if (isHomeScreen) {
        MainFab {
          rootNavController.navigate(NavGraphs.AddExpense(it.title))
        }
      }
    },
    bottomBar = {
      if (isHomeScreen) {
        BottomNavigationBar(rootNavController)
      }
    }
  ) { _ ->

    NavHost(
      modifier = Modifier
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
        CategoryScreen()
      }
      composable<NavGraphs.Profile> {
      }
    }
  }
}