package presentation.screen.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.next_button_text
import expensify.composeapp.generated.resources.onboarding_one
import expensify.composeapp.generated.resources.onboarding_screen_one_subtitle_text
import expensify.composeapp.generated.resources.onboarding_screen_one_title_text
import expensify.composeapp.generated.resources.onboarding_screen_three_subtitle_text
import expensify.composeapp.generated.resources.onboarding_screen_three_title_text
import expensify.composeapp.generated.resources.onboarding_screen_two_subtitle_text
import expensify.composeapp.generated.resources.onboarding_screen_two_title_text
import expensify.composeapp.generated.resources.onboarding_three
import expensify.composeapp.generated.resources.onboarding_two
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.composables.PrimaryButton
import presentation.theme.AppColor
import presentation.theme.AppTheme

@Composable
fun GetStartedScreen(onNextButtonClick: () -> Unit) {
  Box(
    modifier = Modifier
      .background(AppColor.mainGreen)
      .windowInsetsPadding(WindowInsets.statusBars)
      .fillMaxSize()
  ) {

    //Background Layer
    Column(
      modifier = Modifier
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      Spacer(modifier = Modifier.weight(3f))

      Spacer(
        modifier = Modifier
          .fillMaxSize()
          .background(AppColor.backgroundGreen, AppTheme.shape.container)
          .weight(7f),
      )
    }

    val totalPages = 3
    val pagerState = rememberPagerState(0) { totalPages }
    var text = remember { "" }
    var subtext = remember { "" }
    var imageResource = remember { Res.drawable.onboarding_one }

    //Pager Layer
    HorizontalPager(
      modifier = Modifier.fillMaxSize(),
      state = pagerState
    ) {

      when (pagerState.currentPage) {
        0 -> {
          text = stringResource(Res.string.onboarding_screen_one_title_text)
          subtext = stringResource(Res.string.onboarding_screen_one_subtitle_text)
          imageResource = Res.drawable.onboarding_one
        }

        1 -> {
          text = stringResource(Res.string.onboarding_screen_two_title_text)
          subtext = stringResource(Res.string.onboarding_screen_two_subtitle_text)
          imageResource = Res.drawable.onboarding_two
        }

        2 -> {
          text = stringResource(Res.string.onboarding_screen_three_title_text)
          subtext = stringResource(Res.string.onboarding_screen_three_subtitle_text)
          imageResource = Res.drawable.onboarding_three
        }
      }

      Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Column(
          modifier = Modifier
            .weight(3f)
            .padding(start = 32.dp, end = 32.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text = text,
            style = AppTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
          )
          Spacer(modifier = Modifier.size(8.dp))
          Text(
            text = subtext,
            style = AppTheme.typography.bodyLargeRegular,
            color = AppTheme.colorScheme.backgroundGreen,
            textAlign = TextAlign.Center,
          )
        }
        Column(
          modifier = Modifier
            .fillMaxSize()
            .weight(7f),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Box(
            modifier = Modifier
              .weight(3f),
            contentAlignment = Alignment.Center
          ) {
            Box(
              modifier = Modifier
                .size(220.dp)
                .clip(CircleShape)
                .background(AppColor.primaryLightGreen)
                .fillMaxSize(),
              contentAlignment = Alignment.Center
            ) {
              Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(imageResource),
                contentDescription = null
              )
            }
          }
          Spacer(Modifier.weight(1f))
        }
      }
    }

    //Indicator Layer
    Column(
      modifier = Modifier
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.weight(8f))
      val alpha by animateFloatAsState(
        targetValue = if (pagerState.currentPage == totalPages - 1) 1f else 0f,
        animationSpec = tween(durationMillis = 500)
      )

      Column(
        Modifier
          .weight(2f)
          .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
      ) {
        PageIndicator(pagerState = pagerState, totalPages = totalPages)
        Spacer(modifier = Modifier.size(16.dp))
        PrimaryButton(
          modifier = Modifier.alpha(alpha),
          label = stringResource(Res.string.next_button_text)
        ) {
          onNextButtonClick()
        }
      }
    }
  }
}

@Composable
fun PageIndicator(modifier: Modifier = Modifier, pagerState: PagerState, totalPages: Int) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    repeat(totalPages) {
      val isSelected = pagerState.currentPage == it
      Box(
        modifier = Modifier
          .size(14.dp)
          .clip(CircleShape)
          .background(if (isSelected) AppColor.mainGreen else AppColor.backgroundGreen)
          .then(
            if (isSelected.not()) Modifier.border(
              2.dp,
              Color.Black,
              CircleShape
            ) else Modifier
          )
          .fillMaxSize()
      ) {

      }
    }
  }
}