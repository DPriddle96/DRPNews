package com.danielpriddle.drpnews.models

/**
 * Article
 *
 * Data class for Article objects
 * @author Dan Priddle
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
