package com.danielpriddle.drpnews.data.models

data class NewsAPIErrorResponse(
    val status: String,
    val code: String,
    val message: String,
)
