package com.danielpriddle.drpnews.data.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielpriddle.drpnews.data.models.Category
import com.danielpriddle.drpnews.data.models.Country
import com.danielpriddle.drpnews.data.models.Language
import com.danielpriddle.drpnews.data.models.Source
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "sources")
data class SourceEntity(
    @PrimaryKey
    val name: String,
    val id: String? = null,
    val description: String? = null,
    val url: String? = null,
    val category: Category? = null,
    val language: Language? = null,
    val country: Country? = null,
) {
    companion object {
        fun fromModel(source: Source): SourceEntity {
            return SourceEntity(
                id = source.id,
                name = source.name,
                description = source.description,
                url = source.url,
                category = source.category,
                language = source.language,
                country = source.country
            )
        }

        fun toModel(entity: SourceEntity): Source {
            return Source(
                id = entity.id,
                name = entity.name,
                description = entity.description,
                url = entity.url,
                category = entity.category,
                language = entity.language,
                country = entity.country
            )
        }
    }
}
