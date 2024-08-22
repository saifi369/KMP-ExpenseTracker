package presentation.screen.accountsetup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.account_setup_screen_balance_title_text
import expensify.composeapp.generated.resources.account_setup_screen_toolbar_title_text
import expensify.composeapp.generated.resources.account_setup_screen_username_placeholder_text
import expensify.composeapp.generated.resources.account_setup_screen_wallet_title_placeholder_text
import expensify.composeapp.generated.resources.continue_button_text
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.composables.NormalTitleText
import presentation.composables.SubtitleMediumText
import presentation.composables.TextButton
import presentation.composables.textFieldColors
import presentation.theme.AppColor


@OptIn(KoinExperimentalAPI::class)
@Composable
fun AccountSetupScreen(onDoneClick: @Composable () -> Unit, onBackButtonClick: () -> Unit) = Box(
    modifier = Modifier.fillMaxSize()
) {
    val viewModel = koinViewModel<AccountSetupVM>()
    val isUserSaved by viewModel.isUserSaved.collectAsState()

    if(isUserSaved){
        onDoneClick()
    }

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
            title = { NormalTitleText(text = stringResource(Res.string.account_setup_screen_toolbar_title_text)) },
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

        var accountName by remember { mutableStateOf("Test account") }
        var walletName by remember { mutableStateOf("test wallet") }
        var walletBalance by remember { mutableStateOf("5000") }

        Column(
            modifier = Modifier
                .weight(3f)
                .align(Alignment.Start),
            verticalArrangement = Arrangement.Bottom
        ) {
            SubtitleMediumText(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(Res.string.account_setup_screen_balance_title_text)
            )

            TextField(
                modifier = Modifier
                    .height(96.dp),
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
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                )
            )
        }

        Column(
            Modifier
                .clip(RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp))
                .background(AppColor.backgroundGreen)
                .fillMaxSize()
                .weight(3f)
                .padding(bottom = 48.dp, start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
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
            }

            val isEnabled by remember {
                derivedStateOf { accountName.isNotEmpty() && walletName.isNotEmpty() && walletBalance.isNotEmpty() }
            }

            TextButton(
                text = stringResource(Res.string.continue_button_text),
                isEnabled = isEnabled
            ) {
                viewModel.createUser(accountName, walletName, walletBalance.toDouble())
            }
        }
    }
}