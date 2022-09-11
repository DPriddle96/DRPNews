package com.danielpriddle.drpnews.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Article
 *
 * Data class for Article objects
 * @author Dan Priddle
 */

@Parcelize
data class Article(
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null,
) : Parcelable
