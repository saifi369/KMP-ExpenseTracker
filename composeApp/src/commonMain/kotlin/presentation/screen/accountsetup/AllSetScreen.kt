import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.ic_checkmark
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import presentation.theme.AppTheme

@Composable
fun AllSetScreen(onNavigateUp: () -> Unit) {

  var animationPlayed by remember { mutableStateOf(false) }

  val checkMarkAlpha by animateFloatAsState(
    targetValue = if (animationPlayed) 1f else 0f,
    animationSpec = tween(durationMillis = 1000, 200)
  )

  LaunchedEffect(Unit) {
    animationPlayed = true
    delay(1500)
    onNavigateUp()
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(AppTheme.colorScheme.mainGreen),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Icon(
        painter = painterResource(Res.drawable.ic_checkmark),
        contentDescription = null,
        tint = AppTheme.colorScheme.backgroundGreen,
        modifier = Modifier
          .size(150.dp)
          .alpha(checkMarkAlpha)
      )

      Spacer(modifier = Modifier.height(20.dp))

      Text(
        modifier = Modifier.alpha(checkMarkAlpha),
        text = "You're all set!",
        style = AppTheme.typography.titleMedium,
      )
    }
  }
}
