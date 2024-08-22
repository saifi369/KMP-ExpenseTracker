package data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.local.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(userEntity: UserEntity): Long

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?
}