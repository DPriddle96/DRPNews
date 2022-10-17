package com.danielpriddle.drpnews.data

import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.database.entities.SourceEntity
import com.danielpriddle.drpnews.data.mappers.toArticleEntity
import com.danielpriddle.drpnews.data.mappers.toArticleModel
import com.danielpriddle.drpnews.data.mappers.toSourceEntity
import com.danielpriddle.drpnews.data.mappers.toSourceModel
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Source
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Test

@ExperimentalCoroutinesApi
class MapperTests {

    private val mockArticle = mockk<Article>(relaxed = true)
    private val mockArticleEntity = mockk<ArticleEntity>(relaxed = true)
    private val mockSource = mockk<Source>(relaxed = true)
    private val mockSourceEntity = mockk<SourceEntity>(relaxed = true)

    @Test
    fun toArticleReturnsArticle() = runTest {
        assertNotNull(toArticleModel(mockArticleEntity, mockSourceEntity))
    }

    @Test
    fun toArticleEntityReturnsEntity() = runTest {
        assertNotNull(toArticleEntity(mockArticle))
    }

    @Test
    fun toSourceReturnsSource() = runTest {
        assertNotNull(toSourceModel(mockSourceEntity))
    }

    @Test
    fun toSourceEntityReturnsEntity() = runTest {
        assertNotNull(toSourceEntity(mockSource))
    }
}