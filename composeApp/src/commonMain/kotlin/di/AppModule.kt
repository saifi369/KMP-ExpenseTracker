package di

import data.UserPrefRepo
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.screen.addexpense.AddExpenseVM
import ui.screen.home.HomeScreenVM
import ui.screen.onboarding.SplashScreenVM

val appModule = module {
    single { UserPrefRepo(get()) }
    viewModelOf(::SplashScreenVM)
    viewModelOf(::AddExpenseVM)
    viewModelOf(::HomeScreenVM)

//    single<ExpenseDatabase> { getExpenseDatabase(get()) }
}