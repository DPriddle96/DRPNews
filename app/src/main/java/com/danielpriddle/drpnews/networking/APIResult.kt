package com.danielpriddle.drpnews.networking

sealed class APIResult<out T : Any>
data class Success<out T : Any>(val data: T) : APIResult<T>()
data class Failure(val error: Throwable?) : APIResult<Nothing>()
