package presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.model.Transaction
import domain.model.TransactionType.EXPENSE
import org.jetbrains.compose.resources.painterResource
import presentation.screen.categories
import presentation.theme.AppTheme

@Composable
fun TransactionItem(
    transaction: Transaction,
) = Box(
    modifier = Modifier.fillMaxSize()
) {

    val amountText = remember {
        if (transaction.transactionType == EXPENSE) "- ${transaction.amount}" else transaction.amount.toString()
    }
    val icon = remember {
        categories.find { it.title == transaction.category }?.icon
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colorScheme.baseLight80, shape = AppTheme.shape.button)
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Box(
            Modifier
                .size(50.dp)
                .clip(AppTheme.shape.button)
                .background(if (transaction.transactionType == EXPENSE) AppTheme.colorScheme.red else AppTheme.colorScheme.green),
            contentAlignment = Alignment.Center
        ) {
            icon?.let {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Column(
            Modifier.padding(start = 16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {

                Text(
                    modifier = Modifier.weight(1f),
                    text = transaction.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = amountText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.bodyLargeSemiBold,
                    color = if (transaction.transactionType == EXPENSE) AppTheme.colorScheme.red else AppTheme.colorScheme.green
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                Modifier.fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = transaction.category,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.bodySmall,
                    color = AppTheme.colorScheme.baseLight20
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = transaction.date,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.bodySmall,
                    color = AppTheme.colorScheme.baseLight20
                )
            }
        }


    }
}