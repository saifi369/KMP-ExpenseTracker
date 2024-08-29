package presentation.screen.floatingactionbutton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import domain.model.TransactionType
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.ic_expense
import expensify.composeapp.generated.resources.ic_income
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import presentation.composables.LabelSmall
import presentation.theme.AppColor


@Composable
fun MainFab(onClick: (TransactionType) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    val items = listOf(
        MiniFabItem(Res.drawable.ic_income, "Income"),
        MiniFabItem(Res.drawable.ic_expense, "Expense")
    )

    Column(horizontalAlignment = Alignment.End) {
        AnimatedVisibility(
            expanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it }) + expandVertically(),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it }) + shrinkVertically(),
        ) {
            LazyColumn(
                Modifier.padding(16.dp)
            ) {
                items(items) {
                    FabItemUI(it) {
                        onClick(if (it.title == "Income") TransactionType.INCOME else TransactionType.EXPENSE)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        val transition = updateTransition(targetState = expanded, label = "transition")
        val rotation by transition.animateFloat(label = "rotation") {
            if (it) 315f else 0f
        }

        FloatingActionButton(
            modifier = Modifier.rotate(rotation),
            containerColor = AppColor.mainGreen,
            contentColor = AppColor.onPrimaryCyprus,
            onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.Add, null)
        }
    }
}

@Composable
fun FabItemUI(fabItem: MiniFabItem, onClick: () -> Unit) {

    FloatingActionButton(
        onClick = { onClick() },
        containerColor = AppColor.mainGreen,
        contentColor = AppColor.onPrimaryCyprus,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(fabItem.icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            LabelSmall(text = fabItem.title)
        }
    }
}

data class MiniFabItem(val icon: DrawableResource, val title: String)