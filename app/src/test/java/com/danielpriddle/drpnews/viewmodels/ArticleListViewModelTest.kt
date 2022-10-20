package com.danielpriddle.drpnews.viewmodels

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.Success
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
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
    fun getArticlesGetsData() = runTest {
        coEvery {
            articleRepository.getArticles()
        } returns flow { Success(emptyList<Article>()) }
        sut.getArticles()

        coVerify(exactly = 2) { articleRepository.getArticles() }
    }

    @Test
    fun initArticlesGetsData() = runTest {
        coEvery {
            articleRepository.getArticles()
        } returns flow { Success(emptyList<Article>()) }

        coVerify(exactly = 1) { articleRepository.getArticles() }
    }
}