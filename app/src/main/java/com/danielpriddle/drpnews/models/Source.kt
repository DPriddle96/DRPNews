package com.danielpriddle.drpnews.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Source
 *
 * This data class is part of the Article data class and holds Source objects for Articles
 * @author Dan Priddle
 */

@Parcelize
data class Source(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val url: String? = null,
    val category: Category? = null,
    val language: Language? = null,
    val country: Country? = null,
) : Parcelable
