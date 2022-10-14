package com.danielpriddle.drpnews.data.networking

/**
 * Error handling utility classes based on
 * [Android Networking: Beyond the Basics](https://www.raywenderlich.com/35150454-android-networking-beyond-the-basics/)
 */
sealed class Result<out T : Any>
open class Success<out T : Any>(open val data: T) : Result<T>()
data class LocalSuccess<out T : Any>(override val data: T) : Success<T>(data)
data class RemoteSuccess<out T : Any>(override val data: T) : Success<T>(data)
data class Failure(val error: String) : Result<Nothing>()
