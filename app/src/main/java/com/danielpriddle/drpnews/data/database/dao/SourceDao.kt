package com.danielpriddle.drpnews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.danielpriddle.drpnews.data.models.Source

@Dao
interface SourceDao {
    @Query("SELECT * FROM sources")
    suspend fun getSources(): List<Source>

    @Insert(onConflict = REPLACE)
    suspend fun addSources(sources: List<Source>)
}