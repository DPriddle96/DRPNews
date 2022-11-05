package com.danielpriddle.drpnews.utils

import com.danielpriddle.drpnews.R

sealed class Route(val route: String, val icon: Int, val title: String) {
    object Home : Route("home", R.drawable.ic_home, "Home")
    object Search : Route("search", R.drawable.ic_search_white, "Search")
    object Details : Route("detail", 0, "Details")
    object Settings : Route("settings", R.drawable.ic_settings, "Settings")
}
