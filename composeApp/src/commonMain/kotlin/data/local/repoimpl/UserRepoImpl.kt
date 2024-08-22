package data.local.repoimpl

import data.local.database.dao.UserDao
import data.local.mappers.mapToDomainModel
import data.local.mappers.mapToEntity
import domain.model.User
import domain.repo.UserRepo
import domain.utils.LocalError
import domain.utils.Result

class UserRepoImpl(
    private val userDao: UserDao
) : UserRepo {
    override suspend fun insertUser(user: User): Result<Unit, LocalError> {
        return try {
            userDao.upsert(user.mapToEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(LocalError.USER_NOT_SAVED)
        }
    }

    override suspend fun getUser(): Result<User, LocalError> {
        return try {
            val user = userDao.getUser()?.mapToDomainModel()
            if (user != null) {
                return Result.Success(user)
            }
            return Result.Error(LocalError.USER_NOT_FOUND)
        } catch (e: Exception) {
            Result.Error(LocalError.USER_NOT_FOUND)
        }
    }
}