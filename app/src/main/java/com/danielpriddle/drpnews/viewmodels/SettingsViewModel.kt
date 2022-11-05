package com.danielpriddle.drpnews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.data.preferences.PreferencesKeys
import com.danielpriddle.drpnews.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val preferencesDataStore: PreferencesDataStore) :
    ViewModel(), Logger {

    val isDownloadOverWifiOnly = preferencesDataStore.getPreference(PreferencesKeys.WIFI_ONLY_KEY)
    val isDarkMode = preferencesDataStore.getPreference(PreferencesKeys.DARK_MODE_KEY)

    fun toggleDownloadOverWifiOnly() {
        viewModelScope.launch(IO) {
            preferencesDataStore.togglePreference(PreferencesKeys.WIFI_ONLY_KEY)
        }
    }

    fun toggleDarkMode() {
        viewModelScope.launch(IO) {
            preferencesDataStore.togglePreference(PreferencesKeys.DARK_MODE_KEY)
        }
    }


}