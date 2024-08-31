package presentation.screen.addexpense

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import domain.model.TransactionType
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.add_expense_screen_transaction_successful_text
import expensify.composeapp.generated.resources.ic_checkmark
import expensify.composeapp.generated.resources.save_button_text
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import network.chaintech.ui.datepicker.WheelDatePickerView
import network.chaintech.utils.DateTimePickerView
import network.chaintech.utils.now
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.composables.AppCenterTopBar
import presentation.composables.PrimaryButton
import presentation.composables.outlinedTextFieldColors
import presentation.composables.textFieldTransparentColors
import presentation.screen.categories
import presentation.theme.AppColor
import presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun AddExpenseScreen(
  transactionType: String = TransactionType.INCOME.name,
  onBackButtonClick: () -> Unit
) {
  Box(
    modifier = Modifier.fillMaxSize()
  ) {

    var transactionSuccessful by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    var expenseAmount by remember { mutableStateOf("") }
    var expenseTitle by remember { mutableStateOf("") }
    var expenseCategory by remember { mutableStateOf("") }
    var expenseNote by remember { mutableStateOf("") }

    //Main Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .background(if (transactionType == TransactionType.INCOME.title) AppTheme.colorScheme.green else AppTheme.colorScheme.red)
        .windowInsetsPadding(WindowInsets.statusBars),
    ) {

      val viewModel: AddExpenseVM = koinViewModel()

      val walletList by viewModel.walletsList.collectAsStateWithLifecycle()
      val focusRequester = remember { FocusRequester() }
      val scrollState = rememberScrollState()

      LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
      }

      AppCenterTopBar(label = "Add $transactionType") {
        onBackButtonClick()
      }

      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
      ) {

        Text(
          modifier = Modifier.padding(start = 16.dp),
          text = "How much?",
          style = AppTheme.typography.titleSmall,
          color = AppTheme.colorScheme.baseLight80.copy(alpha = 0.7f)
        )

        TextField(
          modifier = Modifier
            .height(96.dp)
            .focusRequester(focusRequester),
          value = expenseAmount,
          onValueChange = {
            expenseAmount = it
          },
          keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Decimal,
            capitalization = KeyboardCapitalization.Sentences
          ),
          maxLines = 1,
          singleLine = true,
          textStyle = AppTheme.typography.titleXLarge.copy(color = AppTheme.colorScheme.baseLight80),
          placeholder = {
            Text(
              text = "0",
              color = AppTheme.colorScheme.baseLight80,
              style = AppTheme.typography.titleXLarge,
            )
          },
          colors = textFieldTransparentColors
        )

        Column(
          Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.backgroundGreen, AppTheme.shape.container)
            .padding(vertical = 32.dp, horizontal = 16.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.SpaceBetween
        ) {
          OutlinedTextField(
            modifier = Modifier
              .fillMaxWidth(),
            textStyle = AppTheme.typography.bodyLargeRegular,
            value = expenseTitle,
            onValueChange = { expenseTitle = it },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
              imeAction = ImeAction.Next,
              capitalization = KeyboardCapitalization.Sentences
            ),
            colors = outlinedTextFieldColors,
            shape = RoundedCornerShape(16.dp),
            placeholder = {
              TextFieldPlaceholderText(label = "Title")
            }
          )

          OutlinedTextField(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp),
            textStyle = AppTheme.typography.bodyLargeRegular,
            value = expenseNote,
            maxLines = 5,
            onValueChange = { expenseNote = it },
            keyboardOptions = KeyboardOptions(
              imeAction = ImeAction.Done,
              capitalization = KeyboardCapitalization.Sentences
            ),
            colors = outlinedTextFieldColors,
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            placeholder = { TextFieldPlaceholderText(label = "Some note...") },
          )

          var expandCategoryMenu by remember { mutableStateOf(false) }

          ExposedDropdownMenuBox(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp),
            expanded = expandCategoryMenu,
            onExpandedChange = {
              expandCategoryMenu = !expandCategoryMenu
            }
          ) {
            OutlinedTextField(
              modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .fillMaxWidth(),
              textStyle = AppTheme.typography.bodyLargeRegular,
              readOnly = true,
              value = expenseCategory,
              onValueChange = { },
              maxLines = 1,
              shape = RoundedCornerShape(16.dp),
              colors = outlinedTextFieldColors,
              keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
              placeholder = { TextFieldPlaceholderText(label = "Select the category") },
              trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandCategoryMenu) },
            )
            ExposedDropdownMenu(
              expanded = expandCategoryMenu,
              onDismissRequest = {
                expandCategoryMenu = false
              }
            ) {
              categories.forEach { selectionOption ->
                DropdownMenuItem(
                  onClick = {
                    expenseCategory = selectionOption.title
                    expandCategoryMenu = false
                  },
                  text = {
                    Text(
                      text = selectionOption.title,
                      style = AppTheme.typography.bodyLargeRegular
                    )
                  }
                )
              }
            }
          }

          var showDatePicker by remember { mutableStateOf(false) }
          var date by remember {
            mutableStateOf(
              Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
            )
          }

          OutlinedTextField(
            modifier = Modifier
              .padding(top = 16.dp)
              .fillMaxWidth(),
            textStyle = AppTheme.typography.bodyLargeRegular,
            value = date,
            enabled = false,
            onValueChange = {},
            maxLines = 1,
            colors = outlinedTextFieldColors.copy(disabledTextColor = AppTheme.colorScheme.onPrimaryCyprus),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
              IconButton(
                onClick = {
                  //                                showDatePicker = true
                }
              ) {
                Icon(
                  imageVector = Icons.Filled.DateRange,
                  contentDescription = null
                )
              }
            }
          )

          //TODO(): Fix the crash on iOS
          WheelDatePickerView(
            modifier = Modifier.padding(top = 16.dp),
            showDatePicker = showDatePicker,
            height = 200.dp,
            title = "Choose Date",
            titleStyle = MaterialTheme.typography.labelLarge,
            doneLabelStyle = MaterialTheme.typography.labelLarge,
            dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
            yearsRange = 2000..LocalDate.now().year,
            rowCount = 3,
            containerColor = AppColor.backgroundGreen,
            shape = RoundedCornerShape(16.dp),
            onDoneClick = {
              date = it.toString()
              showDatePicker = false
            },
            onDismiss = {
              showDatePicker = false
            }
          )

          val isButtonEnabled by remember { derivedStateOf { expenseTitle.isNotEmpty() && expenseAmount.isNotEmpty() && expenseCategory.isNotEmpty() } }

          PrimaryButton(
            modifier = Modifier
              .align(Alignment.CenterHorizontally)
              .padding(top = 64.dp),
            label = stringResource(Res.string.save_button_text),
            isEnabled = isButtonEnabled
          ) {
            keyboardController?.hide()
            viewModel.saveExpense(
              title = expenseTitle,
              date = date,
              amount = expenseAmount.toDouble(),
              category = expenseCategory,
              message = expenseNote,
              transactionType = transactionType,
              wallet = walletList.first()
            )
            transactionSuccessful = true
          }
        }
      }
    }

    var showCheckmark by remember { mutableStateOf(false) }

    LaunchedEffect(transactionSuccessful) {
      if (transactionSuccessful) {
        showCheckmark = true
        delay(2000)
        showCheckmark = false
        transactionSuccessful = false
        expenseAmount = ""
        expenseTitle = ""
        expenseCategory = ""
        expenseNote = ""
      }
    }

    AnimatedVisibility(
      visible = showCheckmark,
      enter = slideInVertically(
        initialOffsetY = { it },
        animationSpec = tween(durationMillis = 500)
      ),
      exit = slideOutVertically(
        targetOffsetY = { it },
        animationSpec = tween(durationMillis = 500)
      ),
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomCenter)
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(AppTheme.colorScheme.mainGreen, AppTheme.shape.container)
          .padding(horizontal = 16.dp, vertical = 48.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Icon(
            painter = painterResource(Res.drawable.ic_checkmark),
            contentDescription = null,
            modifier = Modifier.size(150.dp),
            tint = AppTheme.colorScheme.backgroundGreen
          )
          Spacer(modifier = Modifier.height(16.dp))
          Text(
            text = stringResource(Res.string.add_expense_screen_transaction_successful_text),
            style = AppTheme.typography.titleSmall
          )
        }
      }
    }
  }
}

@Composable
fun TextFieldPlaceholderText(label: String) {
  Text(
    text = label,
    style = AppTheme.typography.labelPlaceholder,
    color = AppTheme.colorScheme.baseLight20
  )
}