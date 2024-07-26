package ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ui.theme.AppColor
import ui.theme.AppTheme

@Composable
fun LargeTitleText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = AppTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        color = AppColor.onPrimaryCyprus
    )
}