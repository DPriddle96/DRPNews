package com.danielpriddle.drpnews.viewmodels

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.Success
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticleListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val articleRepository = mockk<ArticleRepository>()

    private lateinit var sut: ArticleListViewModel

    @Before
    fun setup() {
        mockkStatic(Log::class)
        sut = ArticleListViewModel(articleRepository)
    }

    @Test
    fun getArticlesGetsData() = runBlocking {
        coEvery {
            articleRepository.getArticles()
        } returns flow { Success(emptyList<Article>()) }
        sut.getArticles()

        // Use coVerify for suspend functions
        // (I'm assuming the repo's getArticles() is a suspend fun here)
        coVerify(exactly = 2) { articleRepository.getArticles() }
    }
}