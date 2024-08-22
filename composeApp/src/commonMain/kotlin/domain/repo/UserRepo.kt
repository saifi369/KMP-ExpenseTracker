package domain.repo

import domain.model.User
import domain.utils.LocalError
import domain.utils.Result

interface UserRepo {
    suspend fun insertUser(user: User): Result<Unit, LocalError>
    suspend fun getUser(): Result<User, LocalError>
}