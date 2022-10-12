package com.danielpriddle.drpnews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.danielpriddle.drpnews.data.database.entities.SourceEntity

@Dao
interface SourceDao {
    @Insert(onConflict = REPLACE)
    suspend fun addSource(sources: SourceEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addSources(sources: List<SourceEntity>)

    @Query("DELETE FROM sources")
    suspend fun deleteSources()
}