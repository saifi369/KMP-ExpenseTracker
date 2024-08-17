package domain

import kotlinx.coroutines.flow.Flow

interface UserPrefRepo {
    suspend fun saveValue(value: Boolean): Boolean
    fun readValue(): Flow<Boolean>
}