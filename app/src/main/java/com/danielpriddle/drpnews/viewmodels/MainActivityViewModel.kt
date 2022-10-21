package com.danielpriddle.drpnews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val preferencesDataStore: PreferencesDataStore) :
    ViewModel(), Logger {

    val isDownloadOverWifiOnly = preferencesDataStore.isDownloadOverWifiOnly().asLiveData()

    fun toggleDownloadOverWifiOnly() {
        viewModelScope.launch(IO) {
            preferencesDataStore.toggleDownloadOverWifiOnly()
        }
    }


}