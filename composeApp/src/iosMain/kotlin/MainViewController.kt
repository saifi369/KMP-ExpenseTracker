import androidx.compose.ui.window.ComposeUIViewController
import di.KoinInitializer
import presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }