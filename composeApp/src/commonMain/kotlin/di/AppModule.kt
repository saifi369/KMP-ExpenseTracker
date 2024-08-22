package di

import data.local.database.ExpenseDatabase
import data.local.database.dao.TransactionDao
import data.local.database.dao.UserDao
import data.local.database.dao.WalletDao
import data.local.repoimpl.TransactionRepoImpl
import data.local.repoimpl.UserPrefRepoImpl
import data.local.repoimpl.UserRepoImpl
import data.local.repoimpl.WalletRepoImpl
import domain.repo.TransactionRepo
import domain.repo.UserPrefRepo
import domain.repo.UserRepo
import domain.repo.WalletRepo
import domain.usecase.CreateWalletUseCase
import domain.usecase.GetUserUseCase
import domain.usecase.GetWalletUseCase
import domain.usecase.SaveUserUseCase
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.screen.accountsetup.AccountSetupVM
import presentation.screen.addexpense.AddExpenseVM
import presentation.screen.home.HomeScreenVM
import presentation.screen.onboarding.SplashScreenVM

val appModule = module {
    single { UserPrefRepoImpl(get()) }
    viewModelOf(::SplashScreenVM)
    viewModelOf(::AddExpenseVM)
    viewModelOf(::HomeScreenVM)
    viewModelOf(::AccountSetupVM)

//    single<ExpenseDatabase> { getExpenseDatabase(get()) }

    single<UserDao> { get<ExpenseDatabase>().userDao() }
    single<TransactionDao> { get<ExpenseDatabase>().transactionDao() }
    single<WalletDao> { get<ExpenseDatabase>().walletDao() }

    single<UserPrefRepo> { UserPrefRepoImpl(get()) }
    single<TransactionRepo> { TransactionRepoImpl(get()) }
    single<UserRepo> { UserRepoImpl(get()) }
    single<WalletRepo> { WalletRepoImpl(get()) }

    single { GetUserUseCase(get()) }
    single { SaveUserUseCase(get()) }
    single { CreateWalletUseCase(get()) }
    single { GetWalletUseCase(get()) }

}