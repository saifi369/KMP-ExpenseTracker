package domain.usecase

import domain.model.User
import domain.repo.UserRepo
import domain.utils.LocalError
import domain.utils.Result

class GetUserUseCase(private val userRepo: UserRepo) {
    suspend operator fun invoke(): Result<User, LocalError> {
        return userRepo.getUser()
    }
}