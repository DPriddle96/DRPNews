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

        //get the articles from the InMemoryNewsService and filter null articles
        val articles = newsService.getArticles().filterNotNull()

        //loop through the articles and add a custom ArticleView to the root view for each article
        articles.forEach { article ->
            val articleView = ArticleView(this)
            articleView.setArticle(article)
            //NOTE: <merge> messes with orientation and adding parentTag didn't seem to work.
            //Is there a better way that I am missing?
            articleView.orientation = LinearLayout.VERTICAL
            binding.articleViewGroup.addView(articleView)
        }
    }


}
