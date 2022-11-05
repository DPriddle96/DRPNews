package com.danielpriddle.drpnews.utils

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.*

/**
 * Function to help simplify the creation of Toast messages. Was used in
 * [Android Networking: Beyond the Basics](https://www.raywenderlich.com/35150454-android-networking-beyond-the-basics/)
 * and really liked having to only pass 1 parameter instead of 3 :)
 */


fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun NavHostController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}