package presentation.screen

import expensify.composeapp.generated.resources.Res
import expensify.composeapp.generated.resources.ic_category_entertainment
import expensify.composeapp.generated.resources.ic_category_food
import expensify.composeapp.generated.resources.ic_category_gifts
import expensify.composeapp.generated.resources.ic_category_groceries
import expensify.composeapp.generated.resources.ic_category_medicine
import expensify.composeapp.generated.resources.ic_category_more
import expensify.composeapp.generated.resources.ic_category_rent
import expensify.composeapp.generated.resources.ic_category_savings
import expensify.composeapp.generated.resources.ic_category_transport
import expensify.composeapp.generated.resources.ic_category_salary
import expensify.composeapp.generated.resources.ic_category_subscription
import presentation.screen.category.CategoryItem

val categories = listOf(
    CategoryItem(title = "Food", icon = Res.drawable.ic_category_food),
    CategoryItem(title = "Transport", icon = Res.drawable.ic_category_transport),
    CategoryItem(title = "Medicine", icon = Res.drawable.ic_category_medicine),
    CategoryItem(title = "Groceries", icon = Res.drawable.ic_category_groceries),
    CategoryItem(title = "Rent", icon = Res.drawable.ic_category_rent),
    CategoryItem(title = "Gifts", icon = Res.drawable.ic_category_gifts),
    CategoryItem(title = "Savings", icon = Res.drawable.ic_category_savings),
    CategoryItem(title = "Entertainment", icon = Res.drawable.ic_category_entertainment),
    CategoryItem(title = "Salary", icon = Res.drawable.ic_category_salary),
    CategoryItem(title = "Subscriptions", icon = Res.drawable.ic_category_subscription),
    CategoryItem(title = "More", icon = Res.drawable.ic_category_more),
)