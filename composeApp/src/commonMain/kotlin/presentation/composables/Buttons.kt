package presentation.composables

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import presentation.theme.AppColor

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    onClick: () -> Unit
) {
    Button(
        enabled = isEnabled,
        modifier = modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColor.mainGreen,
            contentColor = AppColor.onPrimaryCyprus
        ),
        onClick = { onClick() }
    ) {
        SubtitleMediumText(text = text)
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