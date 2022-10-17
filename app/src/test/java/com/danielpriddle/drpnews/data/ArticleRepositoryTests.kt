package com.danielpriddle.drpnews.data

import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.*
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.data.repository.ArticleRepositoryImpl
import com.danielpriddle.drpnews.utils.NetworkStatusChecker
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleRepositoryTests {
    private val mockAPINewsService = mockk<APINewsService>()
    private val mockArticleDao = mockk<ArticleDao>(relaxed = true)
    private val mockSourceDao = mockk<SourceDao>()
    private val mockDataStore = mockk<PreferencesDataStore>()
    private val mockNetworkStatusChecker = mockk<NetworkStatusChecker>()

    private val sut = ArticleRepositoryImpl(mockAPINewsService,
        mockArticleDao,
        mockSourceDao,
        mockDataStore,
        mockNetworkStatusChecker)

    @Test
    fun getArticlesEmitsLocalSuccess() = runTest {
        sut.getArticles().onEach { result ->
            assertEquals(LocalSuccess(emptyList<Article>()), result)
        }
    }

    @Test
    fun getArticlesEmitsRemoteSuccess() = runTest {
        sut.getArticles().onEach { result ->
            assertEquals(RemoteSuccess(emptyList<Article>()), result)
        }
    }

    @Test
    fun searchArticlesReturnsData() = runTest {
        val articles = sut.searchArticles("")

        assertEquals(emptyList<Article>(), articles)
    }
}