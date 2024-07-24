package ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.ExpensifyApp

@Composable
@Preview
fun App() {
    MaterialTheme {
        ExpensifyApp()
    }
}