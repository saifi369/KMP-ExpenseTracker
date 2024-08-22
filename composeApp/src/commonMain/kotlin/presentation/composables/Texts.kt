package presentation.composables

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import presentation.theme.AppColor

@Composable
fun LargeTitleText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = AppColor.onPrimaryCyprus,
    textAlign: TextAlign = TextAlign.Center,
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
        selectionColors = TextSelectionColors(
            handleColor = AppColor.mainGreen,
            backgroundColor = AppColor.mainGreen
        ),
        cursorColor = AppColor.mainGreen,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        focusedLabelColor = AppColor.mainGreen,
        unfocusedLabelColor = AppColor.mainGreen,
        focusedContainerColor = AppColor.primaryLightGreen,
        unfocusedContainerColor = AppColor.primaryLightGreen,
        disabledContainerColor = AppColor.primaryLightGreen,
        disabledTrailingIconColor = AppColor.onPrimaryCyprus,
        focusedTrailingIconColor = AppColor.onPrimaryCyprus,
        unfocusedTrailingIconColor = AppColor.onPrimaryCyprus,
    )