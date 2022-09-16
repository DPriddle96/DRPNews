package com.danielpriddle.drpnews.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielpriddle.drpnews.databinding.FragmentArticleListBinding
import com.danielpriddle.drpnews.services.ArticleDataManager
import com.danielpriddle.drpnews.adapters.ArticleListAdapter

/**
 * ArticleListFragment
 *
 * This Fragment displays a list of Articles in a RecyclerView. It reads the Article data from the
 * ArticleDataManager and passes that on to the ArticleListAdapter to bind the data to the
 * RecyclerView. It also sets up an interface for the MainActivity to implement to respond to
 * Article clicks.
 * @author Dan Priddle
 */
class ArticleListFragment : Fragment() {

    private lateinit var articleDataManager: ArticleDataManager
    private lateinit var binding: FragmentArticleListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articles = articleDataManager.readArticles()
        binding.articleListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.articleListRecyclerView.adapter = ArticleListAdapter(articles) { article ->
            val action = ArticleListFragmentDirections.actionListToArticle(article)
            findNavController().navigate(action)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        articleDataManager = ArticleDataManager(context)
    }
}