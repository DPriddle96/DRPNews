package com.danielpriddle.drpnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielpriddle.drpnews.databinding.ActivityMainBinding
import com.danielpriddle.drpnews.services.ArticleDataManager
import com.danielpriddle.drpnews.services.InMemoryNewsService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newsService = InMemoryNewsService()
    private lateinit var articleDataManager: ArticleDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding setup and display the view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get the articles from the InMemoryNewsService and filter null articles
        val articles = newsService.getArticles().filterNotNull()

        //Initialize the ArticleDataManager and save the articles in SharedPreferences
        articleDataManager = ArticleDataManager(this)
        articleDataManager.saveArticles(articles)

    }


}
