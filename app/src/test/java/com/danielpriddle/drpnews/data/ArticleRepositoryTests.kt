package com.danielpriddle.drpnews.data

import android.util.Log
import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.LocalSuccess
import com.danielpriddle.drpnews.data.models.RemoteSuccess
import com.danielpriddle.drpnews.data.networking.APINewsService
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.data.repository.ArticleRepositoryImpl
import com.danielpriddle.drpnews.utils.NetworkStatusChecker
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleRepositoryTests {
    private val mockAPINewsService = mockk<APINewsService>()
    private val mockArticleDao = mockk<ArticleDao>()
    private val mockSourceDao = mockk<SourceDao>()
    private val mockDataStore = mockk<PreferencesDataStore>()
    private val mockNetworkStatusChecker = mockk<NetworkStatusChecker>()

    private val sut = ArticleRepositoryImpl(mockAPINewsService,
        mockArticleDao,
        mockSourceDao,
        mockDataStore,
        mockNetworkStatusChecker)

    @Before
    fun setup() {
        mockkStatic(Log::class)
    }

    @Test
    fun getArticlesEmitsLocalSuccess() = runTest {
        coEvery {
            mockArticleDao.getArticles()
        } returns emptyList()
        sut.getArticles().onEach { result ->
            assertEquals(LocalSuccess(emptyList<Article>()), result)
        }
    }

    @Test
    fun getArticlesEmitsRemoteSuccess() = runTest {
        coEvery {
            mockArticleDao.getArticles()
        } returns emptyList()
        sut.getArticles().onEach { result ->
            assertEquals(RemoteSuccess(emptyList<Article>()), result)
        }
    }

    @Test
    fun searchArticlesReturnsData() = runTest {
        coEvery {
            mockArticleDao.searchArticles("")
        } returns emptyList()
        val articles = sut.searchArticles("")

        assertEquals(emptyList<Article>(), articles)
    }
}