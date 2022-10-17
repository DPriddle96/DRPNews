package com.danielpriddle.drpnews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.danielpriddle.drpnews.data.database.entities.SourceEntity
import com.danielpriddle.drpnews.data.database.entities.relations.ArticleAndSource
import com.danielpriddle.drpnews.data.database.entities.relations.SourceWithArticles

@Dao
interface SourceDao {
    @Query("SELECT * FROM sources")
    suspend fun getSources(): List<SourceEntity>

    @Insert(onConflict = REPLACE)
    suspend fun addSource(sources: SourceEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addSources(sources: List<SourceEntity>)

    @Transaction
    @Query("SELECT * FROM sources")
    fun getSourcesWithArticles(): List<SourceWithArticles>

    @Query("DELETE FROM sources")
    suspend fun deleteSources()
}