package com.danielpriddle.drpnews.utils

import android.util.Log

interface Logger {
    val logTag: String
        get() = javaClass.simpleName

    fun logDebug(message: String) {
        Log.d(logTag, message)
    }

    fun logError(message: String) {
        Log.e(logTag, message)
    }

    fun logInfo(message: String) {
        Log.i(logTag, message)
    }
}


