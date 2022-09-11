package com.danielpriddle.drpnews.services

import android.content.Context
import androidx.preference.PreferenceManager
import com.danielpriddle.drpnews.models.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ArticleDataManager(context: Context) {
    private val gson = Gson()
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveArticles(articles: List<Article>) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        val articleJson = gson.toJson(articles)
        sharedPreferencesEditor.putString("articles", articleJson)
        sharedPreferencesEditor.apply()
    }

    fun readArticles(): List<Article> {
        val articleListString = sharedPreferences.getString("articles", null)
        val type: Type = object : TypeToken<List<Article>>() {}.type
        return if (articleListString == null) {
            ArrayList()
        } else {
            gson.fromJson(articleListString, type) as ArrayList<Article>
        }

    }
}