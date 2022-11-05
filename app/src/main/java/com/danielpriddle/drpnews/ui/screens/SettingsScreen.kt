package com.danielpriddle.drpnews.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.ui.components.SettingToggle
import com.danielpriddle.drpnews.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    val isDownloadOverWifiOnly = settingsViewModel.isDownloadOverWifiOnly.collectAsState().value
    val isDarkMode = settingsViewModel.isDarkMode.collectAsState().value
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = stringResource(id = R.string.settings), style = MaterialTheme.typography.h4)
        SettingToggle(
            checked = isDownloadOverWifiOnly,
            onCheckedChange = { settingsViewModel.toggleDownloadOverWifiOnly() },
            settingResId = R.string.download_only_over_wifi
        )
        SettingToggle(
            checked = isDarkMode,
            onCheckedChange = { settingsViewModel.toggleDarkMode() },
            settingResId = R.string.dark_mode_switch
        )
    }

}