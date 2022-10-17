package com.danielpriddle.drpnews.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.danielpriddle.drpnews.data.database.NewsDatabase
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import com.danielpriddle.drpnews.data.database.entities.SourceEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SourceDaoTest {
    private lateinit var newsDatabase: NewsDatabase
    private lateinit var sourceDao: SourceDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun buildNewsDatabase() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        newsDatabase = Room.inMemoryDatabaseBuilder(context,
            NewsDatabase::class.java).build()
        sourceDao = newsDatabase.sourceDao()
        sourceDao.addSources(SourceListFactory.makeSourceListSeed())
    }

    @After
    fun closeDatabase() {
        newsDatabase.close()
    }

    @Test
    fun getSources() = runBlocking {
        val sources = sourceDao.getSources()
        assertEquals(sources.size, 3)
    }

    @Test
    fun addSources() = runBlocking {
        sourceDao.addSources(SourceListFactory.makeSourceListToAdd())
        assertEquals(sourceDao.getSources().size, 6)
    }

    @Test
    fun addSource() = runBlocking {
        val sourceToAdd = SourceEntity(
            id = "7",
            name = "Test Source 7"
        )
        sourceDao.addSource(sourceToAdd)
        assertEquals(sourceDao.getSources().size, 4)
    }

    @Test
    fun deleteSources() = runBlocking {
        sourceDao.deleteSources()
        assertEquals(sourceDao.getSources().size, 0)
    }
}