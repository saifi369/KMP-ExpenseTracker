package presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.inter_bold
import expensify.composeapp.generated.resources.inter_medium
import expensify.composeapp.generated.resources.inter_semibold
import expensify.composeapp.generated.resources.inter_regular
import org.jetbrains.compose.resources.Font


data class AppColorScheme(
    val base: Color,
    val baseLight80: Color,
    val baseLight20: Color,
    val violet: Color,
    val blue: Color,
    val lightBlue: Color,
    val red: Color,
    val green: Color,
    val yellow: Color,
    val backgroundGreen: Color,
    val primaryLightGreen: Color,
    val onPrimaryCyprus: Color,
    val mainGreen: Color
)

data class AppTypography(
    val titleXLarge: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyLargeRegular: TextStyle,
    val bodyLargeSemiBold: TextStyle,
    val bodySmall: TextStyle,
    val labelSmall: TextStyle,
    val labelTiny: TextStyle,
    val labelPlaceholder: TextStyle,
)

data class AppShape(
    val container: Shape,
    val button: Shape,
    val textField: Shape,
    val icon: Shape
)

data class AppSize(
    val large: Dp,
    val medium: Dp,
    val small: Dp,
)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        base = Color.Unspecified,
        baseLight80 = Color.Unspecified,
        baseLight20 = Color.Unspecified,
        violet = Color.Unspecified,
        blue = Color.Unspecified,
        lightBlue = Color.Unspecified,
        red = Color.Unspecified,
        green = Color.Unspecified,
        yellow = Color.Unspecified,
        backgroundGreen = Color.Unspecified,
        primaryLightGreen = Color.Unspecified,
        onPrimaryCyprus = Color.Unspecified,
        mainGreen = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleXLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleLarge = TextStyle.Default,
        titleSmall = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        bodyLargeRegular = TextStyle.Default,
        bodyLargeSemiBold = TextStyle.Default,
        bodySmall = TextStyle.Default,
        labelSmall = TextStyle.Default,
        labelTiny = TextStyle.Default,
        labelPlaceholder = TextStyle.Default,
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RectangleShape,
        button = RectangleShape,
        textField = RectangleShape,
        icon = RectangleShape
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        small = Dp.Unspecified,
    )
}

@Composable
fun getAppTypography(): AppTypography {
    val interFontFamily = FontFamily(
        Font(Res.font.inter_regular, FontWeight.Normal),
        Font(Res.font.inter_medium, FontWeight.Medium),
        Font(Res.font.inter_semibold, FontWeight.SemiBold),
        Font(Res.font.inter_bold, FontWeight.Bold),
    )

    return AppTypography(
        titleXLarge = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 80.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 39.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        titleSmall = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        bodyLarge = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        ),
        bodyLargeSemiBold = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        bodyLargeRegular = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        ),
        bodySmall = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
        ),
        labelTiny = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 12.sp,
        ),
        labelPlaceholder = TextStyle(
            fontFamily = interFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 18.sp,
        )
    )
}