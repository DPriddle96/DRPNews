package com.danielpriddle.drpnews.models

/**
 * Data class for Article objects to be displayed
 *
 */
data class Article(
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null
)
