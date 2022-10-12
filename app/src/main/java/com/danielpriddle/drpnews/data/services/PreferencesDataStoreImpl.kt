package com.danielpriddle.drpnews.data.services

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.danielpriddle.drpnews.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferencesDataStoreImpl(private val dataStore: DataStore<Preferences>) :
    PreferencesDataStore {

    override fun isDownloadOverWifiOnly() = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.WIFI_ONLY_KEY] ?: false }


    override suspend fun toggleDownloadOverWifiOnly() {
        dataStore.edit {
            it[PreferencesKeys.WIFI_ONLY_KEY] = !(it[PreferencesKeys.WIFI_ONLY_KEY] ?: false)
        }
    }

    private object PreferencesKeys {
        val WIFI_ONLY_KEY = booleanPreferencesKey("download_over_wifi_only_enabled")
    }
}