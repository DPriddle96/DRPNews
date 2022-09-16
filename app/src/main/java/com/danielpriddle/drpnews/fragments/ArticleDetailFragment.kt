package com.danielpriddle.drpnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielpriddle.drpnews.databinding.FragmentArticleDetailBinding
import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.views.ArticleView

/**
 * ArticleDetailFragment
 *
 * This Fragment displays detailed information about Articles abd is created when an Article is
 * clicked in the RecyclerView displayed by the ArticleListFragment. It takes in an Article as an
 * argument and displays a custom ArticleView.
 * @author Dan Priddle
 */
class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val safeArgs = ArticleDetailFragmentArgs.fromBundle(it)
            article = safeArgs.article
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)

        //Add a custom ArticleView to the view, displaying the details of the article passed in
        val articleView = ArticleView(container!!.context)
        articleView.setArticle(article)
        binding.articleViewGroup.addView(articleView)
        return binding.root
    }

}