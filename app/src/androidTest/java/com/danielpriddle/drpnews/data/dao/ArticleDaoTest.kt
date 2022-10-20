package com.danielpriddle.drpnews.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.danielpriddle.drpnews.data.database.NewsDatabase
import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {
    private lateinit var newsDatabase: NewsDatabase
    private lateinit var articleDao: ArticleDao
    private lateinit var sourceDao: SourceDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun buildNewsDatabase() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        newsDatabase = Room.inMemoryDatabaseBuilder(context,
            NewsDatabase::class.java).build()
        articleDao = newsDatabase.articleDao()
        sourceDao = newsDatabase.sourceDao()
        sourceDao.addSources(SourceListFactory.makeSourceListSeed())
        articleDao.addArticles(ArticleListFactory.makeArticleListSeed())
    }

    @After
    fun closeDatabase() {
        newsDatabase.close()
    }

    @Test
    fun getArticles() = runBlocking {
        val articles = articleDao.getArticles()
        assertEquals(articles.size, 3)
    }

    @Test
    fun addArticles() = runBlocking {
        articleDao.addArticles(ArticleListFactory.makeArticleListToAdd())
        assertEquals(articleDao.getArticles().size, 6)
    }

    @Test
    fun searchArticles() = runBlocking {
        val searchString = "Test Title 1"
        val article = articleDao.searchArticles(searchString).first()
        assertNotNull(article)
    }

    @Test
    fun deleteArticles() = runBlocking {
        articleDao.deleteArticles()
        assertEquals(articleDao.getArticles().size, 0)
    }
}