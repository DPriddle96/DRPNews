package com.danielpriddle.drpnews.data.dao

import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.mappers.toSourceEntity
import com.danielpriddle.drpnews.data.models.Source

object ArticleListFactory {
    fun makeArticleListSeed(): List<ArticleEntity> {
        return listOf(
            ArticleEntity(
                source = "Test Source 1",
                title = "Test Title 1",
                url = "https://test1.com",
                publishedAt = "10/17/2022"
            ),
            ArticleEntity(
                source = "Test Source 2",
                title = "Test Title 2",
                url = "https://test2.com",
                publishedAt = "10/17/2022"
            ),
            ArticleEntity(
                source = "Test Source 3",
                title = "Test Title 3",
                url = "https://test3.com",
                publishedAt = "10/17/2022"
            )
        )
    }

    fun makeArticleListToAdd(): List<ArticleEntity> {
        return listOf(
            ArticleEntity(
                source = "Test Source 1",
                title = "Test Title 4",
                url = "https://test4.com",
                publishedAt = "10/17/2022"
            ),
            ArticleEntity(
                source = "Test Source 2",
                title = "Test Title 5",
                url = "https://test5.com",
                publishedAt = "10/17/2022"
            ),
            ArticleEntity(
                source = "Test Source 3",
                title = "Test Title 6",
                url = "https://test6.com",
                publishedAt = "10/17/2022"
            )
        )
    }
}