package presentation.screen.accountsetup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.account_setup_welcome_screen_subtitle_text
import expensify.composeapp.generated.resources.account_setup_welcome_screen_title_text
import expensify.composeapp.generated.resources.continue_button_text
import org.jetbrains.compose.resources.stringResource
import presentation.composables.SecondaryButton
import presentation.theme.AppColor
import presentation.theme.AppTheme

@Composable
fun AccountSetupWelcomeScreen(
  onNextButtonClick: () -> Unit
) = Box(
  modifier = Modifier.fillMaxSize()
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(AppColor.mainGreen)
      .padding(vertical = 48.dp, horizontal = 16.dp),
    verticalArrangement = Arrangement.SpaceBetween
  ) {

    Column(
      modifier = Modifier
        .fillMaxSize(),
      verticalArrangement = Arrangement.SpaceAround
    ) {
      Spacer(modifier = Modifier.weight(1f))
      Column(
        modifier = Modifier.weight(2f)
      ) {
        Text(
          text = stringResource(Res.string.account_setup_welcome_screen_title_text),
          textAlign = TextAlign.Start,
          style = AppTheme.typography.bodyLarge.copy(fontSize = 36.sp)
        )
        Spacer(Modifier.size(8.dp))
        Text(
          text = stringResource(Res.string.account_setup_welcome_screen_subtitle_text),
          style = AppTheme.typography.bodyLargeRegular,
          color = AppTheme.colorScheme.backgroundGreen,
        )
      }
      Spacer(
        modifier = Modifier
          .weight(1f)
      )
      SecondaryButton(
        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        label = stringResource(Res.string.continue_button_text)
      ) {
        onNextButtonClick()
      }
    }
  }
}