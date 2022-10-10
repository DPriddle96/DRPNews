package com.danielpriddle.drpnews.data.database

import androidx.room.TypeConverter
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SourceConverter {
    private val gson = Gson()

    @TypeConverter
    fun toJson(source: Source): String {
        return gson.toJson(source)
    }

    @TypeConverter
    fun fromJson(source: String): Source {
        return gson.fromJson(source, Source::class.java)
    }
}