package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.AppColor.backgroundGreen
import ui.theme.AppColor.lightBlue
import ui.theme.AppColor.mainGreen
import ui.theme.AppColor.onPrimaryCyprus
import ui.theme.AppColor.primaryLightGreen
import ui.theme.AppColor.secondaryOceanBlue
import ui.theme.AppColor.vividBlue

//
//private val darkColorScheme = AppColorScheme(
////    background = Color.Black,
////    onBackground = Purple80,
////    primary = PurpleGrey40,
////    onPrimary = PurpleGrey80,
////    secondary = Pink40,
////    onSecondary = Pink80
//)

private val lightColorScheme = AppColorScheme(
    background = backgroundGreen,
    primary = primaryLightGreen,
    onPrimary = onPrimaryCyprus,
    secondary = secondaryOceanBlue,
    caribbeanGreen = mainGreen,
    lightBlue = lightBlue,
    vividBlue = vividBlue,
    oceanBlue = secondaryOceanBlue,
)

private val typography = AppTypography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    titleNormal = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    paragraph = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    subtitle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    subtext = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 14.sp
    )
)

private val shape = AppShape(
    container = RoundedCornerShape(12.dp),
    button = RoundedCornerShape(50)
)

private val size = AppSize(
    large = 24.dp,
    medium = 16.dp,
    small = 8.dp
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
//    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme

    CompositionLocalProvider(
        LocalAppColorScheme provides lightColorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        LocalAppSize provides size,
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

    val size: AppSize
        @Composable get() = LocalAppSize.current
}