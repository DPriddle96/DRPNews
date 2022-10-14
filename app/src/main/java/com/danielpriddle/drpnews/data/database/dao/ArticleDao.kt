package com.danielpriddle.drpnews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.database.entities.relations.ArticleAndSource

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<ArticleAndSource>

    @Insert(onConflict = REPLACE)
    suspend fun addArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles WHERE title LIKE :search")
    suspend fun searchArticles(search: String): List<ArticleAndSource>

    @Query("DELETE FROM articles")
    suspend fun deleteArticles()
}