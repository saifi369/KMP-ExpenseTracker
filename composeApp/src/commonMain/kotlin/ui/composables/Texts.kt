package ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import ui.theme.AppColor

@Composable
fun LargeTitleText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = AppColor.onPrimaryCyprus,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        color = textColor
    )
}

@Composable
fun NormalTitleText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        color = AppColor.onPrimaryCyprus
    )
}

@Composable
fun ParagraphText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        color = AppColor.onPrimaryCyprus
    )
}

@Composable
fun SubtitleMediumText(modifier: Modifier = Modifier, text: String) {

    val isExpense = try {
        if (text.toDouble() < 0)
            true
        else false
    } catch (e: Exception) {
        false
    }

    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center,
        color = if (isExpense) AppColor.secondaryOceanBlue else AppColor.onPrimaryCyprus,
    )
}

@Composable
fun SubtitleNormalText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelSmall,
        textAlign = TextAlign.Center,
        color = AppColor.onPrimaryCyprus
    )
}

@Composable
fun LabelNormalRegular(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center,
        color = AppColor.onPrimaryCyprus
    )
}

@Composable
fun LabelSmall(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelSmall,
        textAlign = TextAlign.Center,
        color = AppColor.onPrimaryCyprus
    )
}

@Composable
fun LabelNormalBold(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelLarge,
        textAlign = TextAlign.Center,
        color = AppColor.onPrimaryCyprus
    )
}

val textFieldColors: TextFieldColors
    @Composable get() = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLabelColor = AppColor.mainGreen,
        unfocusedLabelColor = AppColor.mainGreen,
        focusedContainerColor = AppColor.primaryLightGreen,
        unfocusedContainerColor = AppColor.primaryLightGreen,
    )