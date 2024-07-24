package ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.profile_pic
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen() {
    Column {
        HomeTitleItem()
    }
}


@Composable
fun HomeTitleItem() {
    Card(
        modifier = Modifier
            .padding(all = 16.dp)
            .height(80.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(Res.drawable.profile_pic),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape),
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Hello,", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "M. Saif Ullah", style = MaterialTheme.typography.titleMedium)
            }
            Icon(
                modifier = Modifier.padding(16.dp).size(32.dp),
                imageVector = Icons.Filled.Notifications,
                contentDescription = null
            )
        }
    }
}