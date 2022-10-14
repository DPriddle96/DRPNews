package com.danielpriddle.drpnews.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielpriddle.drpnews.data.models.Category
import com.danielpriddle.drpnews.data.models.Country
import com.danielpriddle.drpnews.data.models.Language

@Entity(tableName = "sources")
data class SourceEntity(
    val id: String? = null,
    @PrimaryKey
    val name: String,
    val description: String? = null,
    val url: String? = null,
    val category: Category? = null,
    val language: Language? = null,
    val country: Country? = null,
) {

}