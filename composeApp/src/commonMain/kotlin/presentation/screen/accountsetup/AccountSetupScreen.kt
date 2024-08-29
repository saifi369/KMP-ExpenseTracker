package presentation.screen.accountsetup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.account_setup_screen_balance_title_text
import expensify.composeapp.generated.resources.account_setup_screen_toolbar_title_text
import expensify.composeapp.generated.resources.account_setup_screen_username_placeholder_text
import expensify.composeapp.generated.resources.account_setup_screen_wallet_title_placeholder_text
import expensify.composeapp.generated.resources.continue_button_text
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.composables.AppCenterTopBar
import presentation.composables.PrimaryButton
import presentation.composables.SubtitleMediumText
import presentation.composables.textFieldColors
import presentation.composables.textFieldTransparentColors
import presentation.theme.AppColor
import presentation.theme.AppTheme

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AccountSetupScreen(
    onDoneClick: () -> Unit,
    onBackButtonClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .background(AppColor.mainGreen),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {

    val viewModel = koinViewModel<AccountSetupVM>()
    val isUserSaved by viewModel.isUserSaved.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    if (isUserSaved) {
        LaunchedEffect(Unit) {
            keyboardController?.hide()
            onDoneClick()
        }
    }

    AppCenterTopBar(
        label = stringResource(Res.string.account_setup_screen_toolbar_title_text),
        startIcon = Icons.AutoMirrored.Filled.ArrowBack,
        onStartIconClick = onBackButtonClick
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        var accountName by remember { mutableStateOf("") }
        var walletName by remember { mutableStateOf("") }
        var walletBalance by remember { mutableStateOf("") }

        SubtitleMediumText(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.Start),
            text = stringResource(Res.string.account_setup_screen_balance_title_text)
        )

        TextField(
            modifier = Modifier
                .height(96.dp)
                .align(Alignment.Start),
            value = walletBalance,
            onValueChange = {
                walletBalance = it
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Decimal
            ),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 48.sp,
            ),
            placeholder = { Text(text = "00.0", fontSize = 48.sp) },
            colors = textFieldTransparentColors
        )

        Column(
            Modifier
                .fillMaxSize()
                .background(AppColor.backgroundGreen, AppTheme.shape.container)
                .padding(bottom = 48.dp, start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                value = accountName,
                onValueChange = { accountName = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                colors = textFieldColors,
                shape = CircleShape,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.account_setup_screen_username_placeholder_text),
                        fontSize = 18.sp
                    )
                }
            )
            TextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                value = walletName,
                onValueChange = { walletName = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                colors = textFieldColors,
                shape = CircleShape,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.account_setup_screen_wallet_title_placeholder_text),
                        fontSize = 18.sp
                    )
                }
            )

            val isEnabled by remember {
                derivedStateOf { accountName.isNotEmpty() && walletName.isNotEmpty() && walletBalance.isNotEmpty() }
            }

            PrimaryButton(
                modifier = Modifier
                    .padding(top = 64.dp),
                text = stringResource(Res.string.continue_button_text),
                isEnabled = isEnabled
            ) {
                viewModel.createUser(accountName, walletName, walletBalance.toDouble())
            }
        }
    }
}