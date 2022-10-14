package com.danielpriddle.drpnews.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * NetworkStatusChecker
 *
 * This class was used in [Android Networking: Fundamentals](https://www.raywenderlich.com/35031245-android-networking-fundamentals)
 * to check if a client device is connected to the internet before performing HTTP requests.
 */
class NetworkStatusChecker(private val connectivityManager: ConnectivityManager?) {

    fun hasInternetConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }

    fun hasWifiConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }


}