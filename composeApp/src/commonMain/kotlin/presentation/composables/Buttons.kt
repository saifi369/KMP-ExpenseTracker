package presentation.composables

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import presentation.theme.AppColor
import presentation.theme.AppTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colorScheme.mainGreen,
            contentColor = AppTheme.colorScheme.onPrimaryCyprus
        ),
        enabled = isEnabled,
        onClick = { onClick() }
    ) {
        Text(text = text, style = AppTheme.typography.titleSmall)
    }
}

@Composable
fun TextButtonOnPrimary(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColor.backgroundGreen,
            contentColor = AppColor.onPrimaryCyprus
        ),
        onClick = { onClick() }
    ) {
        SubtitleMediumText(text = text)
    }
}