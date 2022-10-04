package com.danielpriddle.drpnews.data.networking

/**
 * Error handling utility classes based on
 * [Android Networking: Beyond the Basics](https://www.raywenderlich.com/35150454-android-networking-beyond-the-basics/)
 */
sealed class Result<out T : Any>
data class Success<out T : Any>(val data: T) : Result<T>()
data class Failure(val error: String) : Result<Nothing>()
