package com.danielpriddle.drpnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.databinding.FragmentArticleListBinding
import com.danielpriddle.drpnews.ui.adapters.ArticleListAdapter
import com.danielpriddle.drpnews.utils.State
import com.danielpriddle.drpnews.utils.toast
import com.danielpriddle.drpnews.viewmodels.ArticleListViewModel

/**
 * ArticleListFragment
 *
 * This fragment displays a list of Articles in a RecyclerView. It contains an ArticleListViewModel
 * that handles the retrieving of Article data and responds to changes to exposed ViewModel
 * properties. It observes Article data and passes that on to the ArticleListAdapter to bind the
 * data to the RecyclerView. It also contains a SwipeRefreshLayout that fetches updated data when
 * the user swipes down to refresh.
 * @author Dan Priddle
 */
class ArticleListFragment : Fragment() {

    private lateinit var binding: FragmentArticleListBinding

    private val articleViewModel: ArticleListViewModel by viewModels {
        ArticleListViewModel.Factory()
    }

    //need a global instance of this since data population is now decoupled.
    private val adapter by lazy {
        ArticleListAdapter { article ->
            val action = ArticleListFragmentDirections.actionListToArticle(article)
            findNavController().navigate(action)
        }
    }

    //SwipeRefreshLayout instance to initialize later
    private lateinit var articleRefreshLayout: SwipeRefreshLayout

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

        /*
           initialize the SwipeRefreshLayout and set up a listener that displays a refreshing spinner
           while retrieving Article data from the API
         */
        articleRefreshLayout = binding.articleRefreshLayout
        articleRefreshLayout.setOnRefreshListener {
            articleRefreshLayout.isRefreshing = true
            articleViewModel.getArticles()
        }
        binding.articleListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.articleListRecyclerView.adapter = adapter

        articleViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> binding.loadingView.root.visibility = View.VISIBLE
                is State.Ready -> handleArticles(state.articles)
                is State.Error -> {
                    binding.loadingView.root.visibility = View.GONE
                    activity?.toast(state.error)
                }
            }
        }

    }

    private fun handleArticles(articles: List<Article>) {
        adapter.setArticleData(articles)
        binding.loadingView.root.visibility = View.GONE
        if (articles.isNotEmpty()) {
            if (articleRefreshLayout.isRefreshing) {
                articleRefreshLayout.isRefreshing = false
                activity?.toast("Got some breaking news for ya!")
            }
        }
    }

}