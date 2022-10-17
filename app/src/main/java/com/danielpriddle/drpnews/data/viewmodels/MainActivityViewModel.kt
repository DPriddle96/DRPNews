package com.danielpriddle.drpnews.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.danielpriddle.drpnews.App
import com.danielpriddle.drpnews.utils.Logger
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(), Logger {
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel() as T
        }
    }

    val isDownloadOverWifiOnly = App.prefsDataStore.isDownloadOverWifiOnly().asLiveData()

    fun toggleDownloadOverWifiOnly() {
        viewModelScope.launch(IO) {
            App.prefsDataStore.toggleDownloadOverWifiOnly()
        }
    }


}