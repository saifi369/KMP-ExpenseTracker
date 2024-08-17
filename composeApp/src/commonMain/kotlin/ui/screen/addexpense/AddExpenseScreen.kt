package ui.screen.addexpense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import data.local.database.model.ExpenseEntity
import data.local.database.model.TransactionType
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.composables.NormalTitleText
import ui.composables.SubtitleMediumText
import ui.composables.TextButton
import ui.composables.textFieldColors
import ui.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun AddExpenseScreen(
    transactionType: String,
    onBackButtonClick: () -> Unit,
) = Box(
    modifier = Modifier.fillMaxSize(),
) {

    KoinContext {

        val viewModel = koinViewModel<AddExpenseVM>()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColor.mainGreen),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CenterAlignedTopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = AppColor.backgroundGreen,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Transparent
                ),
                title = { NormalTitleText(text = "Add Expenses") },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackButtonClick() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp))
                    .background(AppColor.backgroundGreen)
                    .fillMaxSize()
                    .weight(9.5f),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(all = 32.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                ) {

                    var expenseTitle by remember { mutableStateOf("") }
                    var expenseAmount by remember { mutableStateOf("") }
                    var expenseCategory by remember { mutableStateOf("") }
                    var expenseMessage by remember { mutableStateOf("") }

                    SubtitleMediumText(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Expense Title"
                    )
                    TextField(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        textStyle = MaterialTheme.typography.labelMedium,
                        value = expenseTitle,
                        onValueChange = { expenseTitle = it },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = AppColor.mainGreen,
                            unfocusedLabelColor = AppColor.mainGreen,
                            focusedContainerColor = AppColor.primaryLightGreen,
                            unfocusedContainerColor = AppColor.primaryLightGreen,
                        ),
                        shape = CircleShape,
                        placeholder = { Text(text = "Enter title") })

                    SubtitleMediumText(
                        modifier = Modifier.padding(top = 24.dp, start = 8.dp),
                        text = "Amount"
                    )
                    TextField(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        textStyle = MaterialTheme.typography.labelMedium,
                        value = expenseAmount,
                        onValueChange = { expenseAmount = it },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        ),
                        colors = textFieldColors,
                        shape = CircleShape,
                        placeholder = { Text(text = "Enter amount") })

                    SubtitleMediumText(
                        modifier = Modifier.padding(top = 24.dp, start = 8.dp),
                        text = "Category"
                    )

                    val options = listOf("Rent", "Groceries", "Food", "Entertainment", "Loan")
                    var expanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            textStyle = MaterialTheme.typography.labelMedium,
                            readOnly = true,
                            value = expenseCategory,
                            onValueChange = { },
                            maxLines = 1,
                            shape = CircleShape,
                            colors = textFieldColors,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            placeholder = { Text("Select the category") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            },
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    onClick = {
                                        expenseCategory = selectionOption
                                        expanded = false
                                    },
                                    text = {
                                        Text(text = selectionOption)
                                    }
                                )
                            }
                        }
                    }

                    val date =
                        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

                    SubtitleMediumText(
                        modifier = Modifier.padding(top = 24.dp, start = 8.dp),
                        text = "Date"
                    )
                    TextField(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        textStyle = MaterialTheme.typography.labelMedium,
                        value = date.toString(),
                        onValueChange = { },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        maxLines = 1,
                        colors = textFieldColors,
                        shape = CircleShape,
                        placeholder = { Text(text = "Select the category") }, trailingIcon = {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(AppColor.mainGreen)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .background(AppColor.mainGreen),
                                    imageVector = Icons.Filled.DateRange,
                                    contentDescription = null
                                )
                            }
                        }
                    )

                    TextField(
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .fillMaxWidth()
                            .height(150.dp),
                        textStyle = MaterialTheme.typography.labelMedium,
                        value = expenseMessage,
                        onValueChange = { expenseMessage = it },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        colors = textFieldColors,
                        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                        placeholder = { Text(text = "Enter Message") },
                    )

                    Spacer(
                        modifier = Modifier
                            .size(16.dp)
                    )
                    TextButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Save"
                    ) {
                        viewModel.saveExpense(
                            ExpenseEntity(
                                title = expenseTitle,
                                date = date.toString(),
                                amount = expenseAmount.toDouble(),
                                category = expenseCategory,
                                message = expenseMessage,
                                transactionType = TransactionType.valueOf(transactionType)
                            )
                        )
                        onBackButtonClick()
                    }
                }
            }
        }
    }
}