package domain.usecase

import domain.model.User
import domain.repo.UserRepo
import domain.utils.LocalError
import domain.utils.Result

class SaveUserUseCase(private val userRepo: UserRepo) {
    suspend operator fun invoke(user: User): Result<Unit, LocalError> {
        return userRepo.insertUser(user)
    }
}