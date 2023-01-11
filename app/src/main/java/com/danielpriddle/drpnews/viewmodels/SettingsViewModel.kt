package com.danielpriddle.drpnews.viewmodels

import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.data.preferences.PreferencesKeys
import com.danielpriddle.drpnews.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
) : Logger {

    private val coroutineScope = CoroutineScope(Job() + IO)

    val isDownloadOverWifiOnly = preferencesDataStore.getPreference(PreferencesKeys.WIFI_ONLY_KEY)
    val isDarkMode = preferencesDataStore.getPreference(PreferencesKeys.DARK_MODE_KEY)

    fun toggleDownloadOverWifiOnly() {
        coroutineScope.launch {
            preferencesDataStore.togglePreference(PreferencesKeys.WIFI_ONLY_KEY)
        }
    }

    fun toggleDarkMode() {
        coroutineScope.launch {
            preferencesDataStore.togglePreference(PreferencesKeys.DARK_MODE_KEY)
        }
    }


}