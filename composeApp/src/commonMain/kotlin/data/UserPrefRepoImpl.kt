package data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import domain.UserPrefRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class UserPrefRepoImpl(
    private val dataStore: DataStore<Preferences>,
) : UserPrefRepo {

    companion object {
        val USER_ON_BOARDED_KEY = booleanPreferencesKey(name = "user_on_boarded_key")
    }

    override suspend fun saveValue(value: Boolean): Boolean = try {
        dataStore.edit { preferences ->
            preferences.set(key = USER_ON_BOARDED_KEY, value = value)
        }
        true
    } catch (e: Exception) {
        println("saveValue() Error: $e")
        false
    }

    override fun readValue(): Flow<Boolean> =
        dataStore.data
            .catch { emptyFlow<Boolean>() }
            .map { preferences ->
                preferences[USER_ON_BOARDED_KEY] ?: false
            }
}