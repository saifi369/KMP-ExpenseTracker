package ui.screen.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.next_button_text
import expensify.composeapp.generated.resources.onboarding_one
import expensify.composeapp.generated.resources.onboarding_screen_one_title_text
import expensify.composeapp.generated.resources.onboarding_screen_two_title_text
import expensify.composeapp.generated.resources.onboarding_two
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.composables.LargeTitleText
import ui.theme.AppColor

@Composable
fun GetStartedScreen(onNextButtonClick: (Boolean) -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(AppColor.mainGreen)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(0.3f))

            Spacer(
                modifier = Modifier
                    .clip(RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp))
                    .background(AppColor.backgroundGreen)
                    .fillMaxSize()
                    .weight(.7f),
            )
        }

        val totalPages = 2
        val pagerState = rememberPagerState(0) { totalPages }
        var text = remember { "" }
        var imageResource = remember { Res.drawable.onboarding_one }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) {

            when (pagerState.currentPage) {
                0 -> {
                    text = stringResource(Res.string.onboarding_screen_one_title_text)
                    imageResource = Res.drawable.onboarding_one
                }

                1 -> {
                    text = stringResource(Res.string.onboarding_screen_two_title_text)
                    imageResource = Res.drawable.onboarding_two
                }
            }

            Column {
                LargeTitleText(
                    text = text,
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 48.dp)
                        .weight(.3f)
                        .wrapContentSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(.7f),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
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
                        AnimatedVisibility(visible = pagerState.currentPage == totalPages - 1) {
                            Spacer(Modifier.size(24.dp))
                            TextButton(onClick = {
                                onNextButtonClick(true)
                            }) {
                                LargeTitleText(text = stringResource(Res.string.next_button_text))
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(9f))
            PageIndicator(Modifier.weight(1f), pagerState, totalPages)
        }
    }
}


@Composable
fun PageIndicator(modifier: Modifier, pagerState: PagerState, totalPages: Int) {
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