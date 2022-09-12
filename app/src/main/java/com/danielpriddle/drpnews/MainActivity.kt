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
        val articles = newsService.getArticles().filterNotNull()

        //Initialize the ArticleDataManager and save the articles in SharedPreferences
        articleDataManager = ArticleDataManager(this)
        articleDataManager.saveArticles(articles)

    }

    /**
     * onArticleClicked
     *
     * This function is the implementation of ArticleListFragment.OnFragmentInteractionListener
     * interface. When an Article is clicked on the RecyclerView in the ArticleListFragment, it
     * facilitates navigation to the ArticleDetailFragment. It gets the article that was clicked
     * and passes it as a parameter to the action.
     * @param article An Article object that was clicked on
     * @param view the view the clicked event was initiated from
     * @return NONE
     */
    override fun onArticleClicked(article: Article, view: View) {
        val action = ArticleListFragmentDirections.actionListToArticle(article)
        view.findNavController().navigate(action)
    }


}
