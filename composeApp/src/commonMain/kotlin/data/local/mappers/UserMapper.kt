package data.local.mappers

import data.local.model.UserEntity
import domain.model.User

fun UserEntity.mapToDomainModel() = User(
    userId = userId,
    name = name
)

fun User.mapToEntity() = UserEntity(
    name = name,
    userId = userId
)