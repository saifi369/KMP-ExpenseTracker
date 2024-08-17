package data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.local.datastore.AppSettings
import data.local.datastore.DATASTORE_FILE_NAME
import data.utils.getDocumentsDirectory

actual fun createDataStore(context: Any?): DataStore<Preferences> = AppSettings.createDataStore(
    productPath = { getDocumentsDirectory() + "/$DATASTORE_FILE_NAME" }
)