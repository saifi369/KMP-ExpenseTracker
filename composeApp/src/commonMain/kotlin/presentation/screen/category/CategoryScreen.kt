package presentation.screen.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import presentation.screen.categories
import presentation.theme.AppTheme

@Composable
fun CategoryScreen() = Column(
    modifier = Modifier.fillMaxSize().background(AppTheme.colorScheme.mainGreen),
    horizontalAlignment = Alignment.CenterHorizontally
) {

    Column(
        modifier = Modifier.padding(all = 32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Categories",
            style = AppTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Manage finance categories",
            style = AppTheme.typography.bodyLargeSemiBold,
            color = AppTheme.colorScheme.primaryLightGreen,
            textAlign = TextAlign.Center
        )
    }

    Column(
        Modifier.clip(AppTheme.shape.container).background(AppTheme.colorScheme.backgroundGreen)
            .fillMaxSize().padding(vertical = 32.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            items(categories) { category ->
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: CategoryItem) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.background(AppTheme.colorScheme.lightBlue, AppTheme.shape.button)
                .padding(all = 16.dp)
        ) {
            Icon(
                modifier = Modifier.size(60.dp),
                painter = painterResource(category.icon),
                contentDescription = null,
                tint = Color.White
            )
        }
        Text(text = category.title, style = AppTheme.typography.bodyLargeRegular)
    }
}

data class CategoryItem(
    val title: String, val icon: DrawableResource
)