package com.danielpriddle.drpnews.data.database.entities.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.database.entities.SourceEntity
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Source
import kotlinx.parcelize.Parcelize
import java.util.*

data class ArticleAndSource(
    @Embedded
    val article: ArticleEntity,
    @Relation(parentColumn = "articleSourceId", entityColumn = "id")
    val source: SourceEntity,
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

        fun toModel(articleAndSource: ArticleAndSource): Article {
            val article = articleAndSource.article
            val source = articleAndSource.source
            return Article(
                source = SourceEntity.toModel(source),
                author = article.author,
                title = article.title,
                description = article.description,
                url = article.url,
                urlToImage = article.urlToImage,
                publishedAt = article.publishedAt,
                content = article.content
            )
        }
    }
}