package com.danielpriddle.drpnews.data.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielpriddle.drpnews.data.models.Article
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val title: String,
    @ColumnInfo(name = "articleSourceId")
    var articleSourceId: String,
    val author: String? = null,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null,
) {
    companion object {
        fun fromModel(article: Article): ArticleEntity {
            return ArticleEntity(
                articleSourceId = SourceEntity.fromModel(article.source).name,
                author = article.author,
                title = article.title,
                description = article.description,
                url = article.url,
                urlToImage = article.urlToImage,
                publishedAt = article.publishedAt,
                content = article.content
            )
        }

        fun toModel(articleEntity: ArticleEntity, sourceEntity: SourceEntity): Article {
            return Article(
                source = SourceEntity.toModel(sourceEntity),
                author = articleEntity.author,
                title = articleEntity.title,
                description = articleEntity.description,
                url = articleEntity.url,
                urlToImage = articleEntity.urlToImage,
                publishedAt = articleEntity.publishedAt,
                content = articleEntity.content
            )
        }
    }
}
