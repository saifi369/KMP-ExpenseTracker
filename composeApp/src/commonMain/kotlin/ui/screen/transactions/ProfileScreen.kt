package ui.screen.transactions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator


data class ProfileScreen(
    val data: String,
) : Screen {

    @Composable
    override fun Content() {
        ProfileScreenContent(data)
    }
}

@Composable
fun ProfileScreenContent(data: String) {

    val navigator = LocalNavigator.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "This is Details Screen")
            Spacer(modifier = Modifier.size(64.dp))
            Text(text = data)
            Spacer(modifier = Modifier.size(64.dp))
            Button(onClick = {
                navigator?.pop()
            }) {
                Text(text = "Goto next screen")
            }
        }
    }
}