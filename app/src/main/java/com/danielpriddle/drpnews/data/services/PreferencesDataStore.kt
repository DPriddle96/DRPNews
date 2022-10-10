package com.danielpriddle.drpnews.data.services

import kotlinx.coroutines.flow.Flow

interface PreferencesDataStore {
    fun isDownloadOverWifiOnly(): Flow<Boolean>
    suspend fun toggleDownloadOverWifiOnly()
}