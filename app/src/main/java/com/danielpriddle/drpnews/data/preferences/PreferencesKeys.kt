package com.danielpriddle.drpnews.data.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKeys {
    const val PREFS_DATASTORE_NAME = "user_preferences"
    val WIFI_ONLY_KEY = booleanPreferencesKey("download_over_wifi_only_enabled")
    val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_enabled")
}