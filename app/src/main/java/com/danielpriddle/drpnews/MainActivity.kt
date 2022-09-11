package com.danielpriddle.drpnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.findNavController
import com.danielpriddle.drpnews.databinding.ActivityMainBinding
import com.danielpriddle.drpnews.fragments.ArticleDetailFragment
import com.danielpriddle.drpnews.fragments.ArticleListFragment
import com.danielpriddle.drpnews.fragments.ArticleListFragmentDirections
import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.services.ArticleDataManager
import com.danielpriddle.drpnews.services.InMemoryNewsService
import com.danielpriddle.drpnews.views.ArticleView

class MainActivity : AppCompatActivity(), ArticleListFragment.OnFragmentInteractionListener {
    private lateinit var binding: ActivityMainBinding
    private val newsService = InMemoryNewsService()
    private lateinit var articleDataManager: ArticleDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding setup and display the view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get the articles from the InMemoryNewsService and filter null articles
        articleDataManager = ArticleDataManager(this)
        val articles = newsService.getArticles().filterNotNull()
        articleDataManager.saveArticles(articles)

        //loop through the articles and add a custom ArticleView to the root view for each article
//        articles.forEach { article ->
//            val articleView = ArticleView(this)
//            articleView.setArticle(article)
//            //NOTE: <merge> messes with orientation and adding parentTag didn't seem to work.
//            //Is there a better way that I am missing?
//            articleView.orientation = LinearLayout.VERTICAL
//            binding.articleViewGroup.addView(articleView)
//        }
    }

    override fun onArticleClicked(article: Article, view: View) {
        val action = ArticleListFragmentDirections.actionListToArticle(article)
        view.findNavController().navigate(action)
    }


}
