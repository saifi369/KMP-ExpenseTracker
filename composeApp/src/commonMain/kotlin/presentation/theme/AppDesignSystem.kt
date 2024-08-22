package presentation.theme

import androidx.compose.material3.Typography
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
import expensify.composeapp.generated.resources.leaguespartan_regular
import expensify.composeapp.generated.resources.poppins_light
import expensify.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font


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
    val titleMedium: TextStyle,
    val bodyMedium: TextStyle,
    val labelMedium: TextStyle,
    val labelLarge: TextStyle,
    val subtitleMedium: TextStyle,
    val subtitleNormal: TextStyle,
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
        titleMedium = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        labelMedium = TextStyle.Default,
        labelLarge = TextStyle.Default,
        subtitleMedium = TextStyle.Default,
        subtitleNormal = TextStyle.Default,
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

val baseline = Typography()

@Composable
fun getTypography(): Typography {
    val bodyFontFamily = FontFamily(Font(Res.font.poppins_light, FontWeight.Light))
    val displayFontFamily = FontFamily(Font(Res.font.poppins_light, FontWeight.SemiBold))
    val titleFontFamily = FontFamily(Font(Res.font.poppins_semibold, FontWeight.SemiBold))
    val subtextFontFamily = FontFamily(Font(Res.font.leaguespartan_regular, FontWeight.Normal))
    return Typography(
        displayLarge = baseline.displayLarge.copy(
            fontFamily = displayFontFamily,
            color = AppColor.onPrimaryCyprus
        ),
        displayMedium = baseline.displayMedium.copy(
            fontFamily = displayFontFamily,
            color = AppColor.onPrimaryCyprus
        ),
        displaySmall = baseline.displaySmall.copy(
            fontFamily = displayFontFamily,
            color = AppColor.onPrimaryCyprus
        ),
        headlineLarge = baseline.headlineLarge.copy(
            fontFamily = displayFontFamily,
            color = AppColor.onPrimaryCyprus
        ),
        headlineMedium = baseline.headlineMedium.copy(
            fontFamily = displayFontFamily,
            color = AppColor.onPrimaryCyprus
        ),
        headlineSmall = baseline.headlineSmall.copy(
            fontFamily = displayFontFamily,
            color = AppColor.onPrimaryCyprus
        ),
        titleLarge = baseline.titleLarge.copy(
            fontFamily = titleFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = AppColor.onPrimaryCyprus
        ),
        titleMedium = baseline.titleMedium.copy(
            fontFamily = titleFontFamily,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColor.onPrimaryCyprus
        ),
        titleSmall = baseline.titleSmall.copy(
            fontFamily = titleFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = AppColor.onPrimaryCyprus
        ),
        bodyLarge = baseline.bodyLarge.copy(
            fontFamily = bodyFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = AppColor.onPrimaryCyprus
        ),
        bodyMedium = baseline.bodyMedium.copy(
            fontFamily = bodyFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = AppColor.onPrimaryCyprus
        ),
        bodySmall = baseline.bodySmall.copy(
            fontFamily = bodyFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = AppColor.onPrimaryCyprus
        ),
        labelLarge = baseline.labelLarge.copy(
            fontFamily = subtextFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = AppColor.onPrimaryCyprus
        ),
        labelMedium = baseline.labelMedium.copy(
            fontFamily = subtextFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = AppColor.onPrimaryCyprus
        ),
        labelSmall = baseline.labelSmall.copy(
            fontFamily = subtextFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = AppColor.onPrimaryCyprus
        ),
    )
}