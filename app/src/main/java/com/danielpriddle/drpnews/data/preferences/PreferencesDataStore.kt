package com.danielpriddle.drpnews.data.preferences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.StateFlow

interface PreferencesDataStore {
    fun getPreference(preferenceKey: Preferences.Key<Boolean>): StateFlow<Boolean>
    suspend fun togglePreference(preferenceKey: Preferences.Key<Boolean>)
}