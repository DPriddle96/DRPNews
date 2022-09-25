package com.danielpriddle.drpnews.networking

/**
 * Error handling utility classes based on
 * [Android Networking: Beyond the Basics](https://www.raywenderlich.com/35150454-android-networking-beyond-the-basics/)
 */
sealed class APIResult<out T : Any>
data class Success<out T : Any>(val data: T) : APIResult<T>()
data class Failure(val error: Throwable?) : APIResult<Nothing>()
