package ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import data.local.model.TransactionType
import domain.model.Transaction
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.ic_expense
import expensify.composeapp.generated.resources.ic_income
import org.jetbrains.compose.resources.painterResource
import ui.composables.LabelNormalBold
import ui.composables.ParagraphText
import ui.composables.SubtitleMediumText
import ui.theme.AppColor

@Composable
fun TransactionItem(
    transaction: Transaction,
) = Box(
    modifier = Modifier.fillMaxSize()
) {

    val amountText =
        if (transaction.transactionType == TransactionType.Expense) "-${transaction.amount}" else transaction.amount.toString()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(AppColor.lightBlue),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(
                    when (transaction.transactionType) {
                        TransactionType.Expense -> Res.drawable.ic_expense
                        TransactionType.Income -> Res.drawable.ic_income
                    }
                ),
                contentDescription = null,
            )
        }

        Column {
            SubtitleMediumText(text = transaction.title)
            Spacer(modifier = Modifier.size(8.dp))
            LabelNormalBold(text = transaction.date)
        }

        Spacer(
            modifier = Modifier
                .width(2.dp)
                .height(50.dp)
                .background(AppColor.mainGreen)
        )

        ParagraphText(text = "Monthly")

        Spacer(
            modifier = Modifier
                .width(2.dp)
                .height(50.dp)
                .background(AppColor.mainGreen)
        )

        SubtitleMediumText(
            text = amountText
        )
    }
}
