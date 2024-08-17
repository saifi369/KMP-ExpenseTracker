package com.expensify.wallet.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.local.datastore.AppSettings
import data.local.datastore.DATASTORE_FILE_NAME

fun createDataStore(context: Context): DataStore<Preferences> = AppSettings.createDataStore(
    productPath = { context.filesDir.resolve(DATASTORE_FILE_NAME).absolutePath }
)