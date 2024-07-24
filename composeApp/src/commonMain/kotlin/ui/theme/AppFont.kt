package ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.poppins_light
import expensify.composeapp.generated.resources.poppins_medium
import expensify.composeapp.generated.resources.poppins_regular
import expensify.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun PoppinsFonts() = FontFamily(
    Font(Res.font.poppins_light, weight = FontWeight.Light),
    Font(Res.font.poppins_medium, weight = FontWeight.Medium),
    Font(Res.font.poppins_regular, weight = FontWeight.Normal),
    Font(Res.font.poppins_semibold, weight = FontWeight.SemiBold),
)
