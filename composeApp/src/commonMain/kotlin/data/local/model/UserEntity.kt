package data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val name: String
)