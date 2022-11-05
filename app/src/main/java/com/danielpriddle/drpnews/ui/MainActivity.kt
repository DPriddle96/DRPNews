package com.danielpriddle.drpnews.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.danielpriddle.drpnews.ui.screens.MainScreen
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme
import com.danielpriddle.drpnews.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            val isDarkMode = viewModel.isDarkMode.collectAsState().value
            DRPNewsTheme(isDarkMode) {
                MainScreen()
            }
        }
    }
}





