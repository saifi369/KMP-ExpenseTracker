import androidx.compose.ui.window.ComposeUIViewController
import di.KoinInitializer
import ui.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }