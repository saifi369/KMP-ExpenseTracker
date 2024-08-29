import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import di.KoinInitializer
import presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
        onFocusBehavior = OnFocusBehavior.DoNothing
    }
) { App() }