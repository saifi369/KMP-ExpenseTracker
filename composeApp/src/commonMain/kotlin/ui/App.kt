package ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.AppContent
import ui.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        AppContent()
    }
}