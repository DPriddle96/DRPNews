package com.danielpriddle.drpnews.services

import android.content.Context
import androidx.preference.PreferenceManager
import com.danielpriddle.drpnews.models.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * ArticleDataManager
 *
 * This class sets up a PreferenceManager for reading and writing Article data to SharedPreferences.
 * Since complex data types cannot be stored in SharedPreferences, Gson was added to convert
 * Article objects to JSON strings when writing, and from JSON strings to Article objects when
 * reading.
 *
 * Credit goes to
 * [Android – Save ArrayList to SharedPreferences with Kotlin](https://www.geeksforgeeks.org/android-save-arraylist-to-sharedpreferences-with-kotlin/)
 * for inspiration.
 * @author Dan Priddle
 */
class ArticleDataManager(context: Context) {
    private val gson = Gson()
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveArticles(articles: List<Article>) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putString("articles", gson.toJson(articles))
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