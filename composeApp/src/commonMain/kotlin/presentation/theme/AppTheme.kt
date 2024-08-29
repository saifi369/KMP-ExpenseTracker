package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//TODO(): update the colors for dark theme
private val darkColorScheme = AppColorScheme(
    base = Color(0xFF91919F),
    baseLight80 = Color(0xFFFCFCFC),
    baseLight20 = Color(0xFF91919F),
    violet = Color(0xFF7F3DFF),
    blue = Color(0xFF0077FF),
    lightBlue = Color(0xFF6DB6FE),
    red = Color(0xFFFD3C4A),
    green = Color(0xFF00A86B),
    yellow = Color(0xFFFCAC12),
    backgroundGreen = Color(0xFFF1FFF3),
    onPrimaryCyprus = Color(0xFF0E3E3E),
    mainGreen = Color(0xFF00D09E),
    primaryLightGreen = Color(0xFFDFF7E2)
)

private val lightColorScheme = AppColorScheme(
    base = Color(0xFF91919F),
    baseLight80 = Color(0xFFFCFCFC),
    baseLight20 = Color(0xFF91919F),
    violet = Color(0xFF7F3DFF),
    blue = Color(0xFF0077FF),
    lightBlue = Color(0xFF6DB6FE),
    red = Color(0xFFFD3C4A),
    green = Color(0xFF00A86B),
    yellow = Color(0xFFFCAC12),
    backgroundGreen = Color(0xFFF1FFF3),
    onPrimaryCyprus = Color(0xFF0E3E3E),
    mainGreen = Color(0xFF00D09E),
    primaryLightGreen = Color(0xFFDFF7E2)
)

private val shape = AppShape(
    container = RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp),
    button = RoundedCornerShape(16.dp),
    icon = RoundedCornerShape(12.dp),
    textField = RoundedCornerShape(16.dp)
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme


    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides getAppTypography(),
        LocalAppShape provides shape,

        content = content
    )
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current
}