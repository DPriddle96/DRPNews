package com.danielpriddle.drpnews.data.database

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SourceConverter {
    private val gson = Gson()
    fun toJson(source: Source): String {
        return gson.toJson(source)
    }

    fun fromJson(source: String): Source {
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(source, type)
    }
}