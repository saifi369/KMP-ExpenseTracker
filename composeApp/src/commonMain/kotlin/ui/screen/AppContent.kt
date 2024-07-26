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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.screen.onboarding.GetStartedScreen
import ui.theme.AppColor

@Composable
fun AppContent() {

    val rootNavController = rememberNavController()

    Surface(
        modifier = Modifier
            .background(AppColor.mainGreen)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        color = AppColor.mainGreen
    ) {
        Scaffold { innerPadding ->
            NavHost(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                navController = rootNavController,
                startDestination = "home"
            ) {
                composable("home") {
                    GetStartedScreen()
                }
            }
        }
    }
}