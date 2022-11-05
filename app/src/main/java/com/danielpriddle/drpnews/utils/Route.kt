package com.danielpriddle.drpnews.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Route(val route: String, val icon: ImageVector, val title: String) {
    object Home : Route("home", Icons.Default.Home, "Home")
    object Search : Route("search", Icons.Default.Search, "Search")
    object Details : Route("detail", Icons.Default.List, "Details")
    object Settings : Route("settings", Icons.Default.Settings, "Settings")
}
