package ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.bottomnavigation.BottomNavigationBar
import ui.screen.home.HomeScreenNavHost

@Composable
fun ExpensifyApp() {

    val rootNavController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {}
                ) {
                    Icon(Icons.Filled.Add, null)
                }
            }
        ) { innerPadding ->
            NavHost(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                navController = rootNavController,
                startDestination = "home"
            ) {
                composable("home") {
                    HomeScreenNavHost()
                }
            }
        }
    }
}