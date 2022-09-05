package com.danielpriddle.drpnews.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.danielpriddle.drpnews.databinding.ActivityMainBinding
import com.danielpriddle.drpnews.services.InMemoryNewsService
import com.danielpriddle.drpnews.views.ArticleView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newsService = InMemoryNewsService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding setup and display the view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //call user-made function to populate the 5 TextViews
        val articles = newsService.getArticles().filterNotNull()
        articles.forEach { article ->
            val articleView = ArticleView(this)
            articleView.setArticle(article)
            articleView.orientation = LinearLayout.VERTICAL
            binding.articleViewGroup.addView(articleView)
        }
    }


}
