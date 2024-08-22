package presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import presentation.theme.AppColor

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().background(AppColor.mainGreen),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(Res.drawable.ic_logo),
            null
        )
    }
}