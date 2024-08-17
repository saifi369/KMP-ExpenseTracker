package data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.local.datastore.AppSettings
import data.local.datastore.DATASTORE_FILE_NAME

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    require(
        value = context is Context,
        lazyMessage = { "Context object is required." }
    )
    return AppSettings.createDataStore(
        productPath = {
            context
                .filesDir
                .resolve(DATASTORE_FILE_NAME)
                .absolutePath
        }
    )
}