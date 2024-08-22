package presentation.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavGraphs {

    @Serializable
    data object Home : NavGraphs()

    @Serializable
    data object Transactions : NavGraphs()

    @Serializable
    data object Profile : NavGraphs()

    @Serializable
    data object Category : NavGraphs()

    @Serializable
    data class AddExpense(val transactionType: String) : NavGraphs()

}