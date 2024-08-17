package di

import data.UserPrefRepoImpl
import data.local.TransactionRepoImpl
import domain.TransactionRepo
import domain.UserPrefRepo
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.screen.addexpense.AddExpenseVM
import ui.screen.home.HomeScreenVM
import ui.screen.onboarding.SplashScreenVM

val appModule = module {
    single { UserPrefRepoImpl(get()) }
    viewModelOf(::SplashScreenVM)
    viewModelOf(::AddExpenseVM)
    viewModelOf(::HomeScreenVM)

//    single<ExpenseDatabase> { getExpenseDatabase(get()) }
    single<UserPrefRepo> { UserPrefRepoImpl(get()) }
    single<TransactionRepo> { TransactionRepoImpl(get()) }
}