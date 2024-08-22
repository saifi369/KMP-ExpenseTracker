package presentation.screen.home

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import domain.model.User
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.ic_check
import expensify.composeapp.generated.resources.ic_expense
import expensify.composeapp.generated.resources.ic_income
import expensify.composeapp.generated.resources.ic_notification
import org.jetbrains.compose.resources.painterResource
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.composables.LabelNormalBold
import presentation.composables.LargeTitleText
import presentation.composables.SubtitleMediumText
import presentation.composables.SubtitleNormalText
import presentation.theme.AppColor

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(viewModel: HomeScreenVM) {

    val transactionList by viewModel.expensesList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val userInfo by viewModel.userInfo.collectAsState()
    val walletsList by viewModel.walletsList.collectAsState()

//    if (isLoading) {
//        CircularProgressIndicator(modifier = Modifier.size(200.dp))
//    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(AppColor.mainGreen)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(horizontal = 16.dp)
                ) {
                    HomeWelcomeItem(userInfo)
                    Spacer(modifier = Modifier.size(32.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BalanceInOutItem(
                            income = true,
                            amount = 12345.45
                        )
                        Spacer(
                            modifier = Modifier
                                .background(AppColor.backgroundGreen)
                                .height(50.dp)
                                .width(2.dp)
                        )
                        BalanceInOutItem(
                            income = false,
                            amount = 6756.003
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(16.dp)
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                Color.Black,
                                RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp)
                            )
                            .fillMaxWidth()
                            .height(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "30%",
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(30.dp)
                                    .background(
                                        AppColor.backgroundGreen,
                                        RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp)
                                    )
                                    .weight(9f),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                SubtitleMediumText(
                                    text = "$5000",
                                    modifier = Modifier.padding(end = 16.dp)
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        SubtitleNormalText(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "30% of your expenses, looks good."
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp))
                        .background(AppColor.backgroundGreen)
                        .fillMaxSize()
                        .weight(.7f),
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
//                }
            }
        }
    }
}

@Composable
fun BalanceInOutItem(
    income: Boolean = true,
    amount: Double = 7783.00,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Image(
                painter = painterResource(if (income) Res.drawable.ic_income else Res.drawable.ic_expense),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            SubtitleMediumText(text = if (income) "Total Balance" else "Total Expense")
        }
        Spacer(modifier = Modifier.size(4.dp))
        LargeTitleText(
            text = if (income) "$$amount" else "-$$amount",
            textColor = if (income) AppColor.primaryLightGreen else AppColor.secondaryOceanBlue,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
fun HomeWelcomeItem(user: User?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            LargeTitleText(text = "Hi, ${user?.name}", textAlign = TextAlign.Start)
            LabelNormalBold(text = "Good Morning")
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
