package data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

@OptIn(InternalCoroutinesApi::class)
object AppSettings {

    private lateinit var dataStore: DataStore<Preferences>
    private val lock = SynchronizedObject()

    fun createDataStore(productPath: () -> String): DataStore<Preferences> {
        return synchronized(lock) {
            if (AppSettings::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = {
                        productPath().toPath()
                    }
                )
            }
        }
    }
}

internal const val DATASTORE_FILE_NAME = "prefs.preferences_pb"