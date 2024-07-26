package ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


data class AppColorScheme(
    val background: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val caribbeanGreen: Color,
    val lightBlue: Color,
    val vividBlue: Color,
    val oceanBlue: Color,
)

data class AppTypography(
    val titleLarge: TextStyle,
    val titleNormal: TextStyle,
    val paragraph: TextStyle,
    val subtitle: TextStyle,
    val subtext: TextStyle,
)

data class AppShape(
    val container: Shape,
    val button: Shape,
)

data class AppSize(
    val large: Dp,
    val medium: Dp,
    val small: Dp,
)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        background = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        caribbeanGreen = Color.Unspecified,
        lightBlue = Color.Unspecified,
        vividBlue = Color.Unspecified,
        oceanBlue = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleNormal = TextStyle.Default,
        paragraph = TextStyle.Default,
        subtitle = TextStyle.Default,
        subtext = TextStyle.Default
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RectangleShape,
        button = RectangleShape
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        small = Dp.Unspecified,
    )
}