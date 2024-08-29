package presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import domain.model.User
import domain.model.Wallet
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.ic_expense_tab
import expensify.composeapp.generated.resources.ic_income_tab
import expensify.composeapp.generated.resources.ic_notification
import org.jetbrains.compose.resources.painterResource
import presentation.composables.LabelNormalBold
import presentation.composables.LargeTitleText
import presentation.theme.AppColor
import presentation.theme.AppTheme

@Composable
fun HomeScreen(viewModel: HomeScreenVM) {

    val transactionList by viewModel.expensesList.collectAsStateWithLifecycle()
    val userInfo by viewModel.userInfo.collectAsStateWithLifecycle()
    val balanceInfo by viewModel.balanceInfo.collectAsStateWithLifecycle()
    val walletsList by viewModel.walletsList.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.mainGreen)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier
                .background(AppColor.mainGreen)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeWelcomeItem(userInfo, walletsList)
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Account Balance",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 16.sp,
                color = AppColor.primaryLightGreen,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$ ${balanceInfo.totalBalance}",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TransactionTabItem(
                    modifier = Modifier.weight(1f),
                    amountType = "Income",
                    balanceInfo.income
                )
                TransactionTabItem(
                    modifier = Modifier.weight(1f),
                    amountType = "Expenses",
                    balanceInfo.expense
                )
            }
            Spacer(Modifier.size(16.dp))
            Box(
                modifier = Modifier
                    .clip(AppTheme.shape.container)
                    .fillMaxWidth()
                    .background(AppTheme.colorScheme.backgroundGreen)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (transactionList.isEmpty()) {
                    LargeTitleText(
                        text = "\uD83D\uDE14\nNothing to show, Add some transactions to ",
                        textAlign = TextAlign.Start
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                            .padding(all = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(transactionList) {
                            TransactionItem(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeWelcomeItem(user: User?, walletsList: List<Wallet>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            LargeTitleText(text = "Hi, ${user?.name}", textAlign = TextAlign.Start)
            if (walletsList.isNotEmpty()) {
                LabelNormalBold(text = walletsList.first().name)
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(AppColor.backgroundGreen, CircleShape)
                .size(30.dp)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(Res.drawable.ic_notification),
                contentDescription = null,
            )
        }
    }
}


@Composable
fun TransactionTabItem(
    modifier: Modifier = Modifier,
    amountType: String = "Income",
    amount: Double
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(all = 8.dp)
            .background(
                color = if (amountType == "Income") AppColor.incomeColor else AppColor.expenseColor,
                shape = RoundedCornerShape(size = 24.dp)
            )
            .padding(all = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                modifier = Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(8.dp),
                painter = painterResource(if (amountType == "Income") Res.drawable.ic_income_tab else Res.drawable.ic_expense_tab),
                contentDescription = null,
                tint = if (amountType == "Income") AppColor.incomeColor else AppColor.expenseColor
            )

            Spacer(modifier = Modifier.size(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = amountType,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier.wrapContentWidth(),
                    text = amount.toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}