package com.danielpriddle.drpnews.data.database

import android.content.Context
import androidx.room.*
import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.database.entities.SourceEntity

private const val DATABASE_VERSION = 1

@Database(
    entities = [ArticleEntity::class, SourceEntity::class],
    version = DATABASE_VERSION,
)
abstract class NewsDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "DRPNews"

        fun buildDatabase(context: Context): NewsDatabase {
            return Room.databaseBuilder(
                context,
                NewsDatabase::class.java,
                DATABASE_NAME,
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun articleDao(): ArticleDao
    abstract fun sourceDao(): SourceDao

}