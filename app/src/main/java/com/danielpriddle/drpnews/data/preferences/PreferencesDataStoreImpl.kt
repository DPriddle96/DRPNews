package com.danielpriddle.drpnews.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.danielpriddle.drpnews.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException

class PreferencesDataStoreImpl(private val dataStore: DataStore<Preferences>) :
    PreferencesDataStore, Logger {

    override fun getPreference(preferenceKey: Preferences.Key<Boolean>) =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[preferenceKey] ?: false }
            .stateIn(CoroutineScope(Dispatchers.IO), SharingStarted.Lazily, false)


    override suspend fun togglePreference(preferenceKey: Preferences.Key<Boolean>) {
        logInfo("Toggling ${preferenceKey.name}...")
        dataStore.edit {
            it[preferenceKey] = !(it[preferenceKey] ?: false)
        }
    }

}