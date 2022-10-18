package com.danielpriddle.drpnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.danielpriddle.drpnews.App
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.LocalSuccess
import com.danielpriddle.drpnews.data.networking.RemoteSuccess
import com.danielpriddle.drpnews.data.networking.Success
import com.danielpriddle.drpnews.databinding.FragmentArticleListBinding
import com.danielpriddle.drpnews.ui.adapters.ArticleListAdapter
import com.danielpriddle.drpnews.utils.Logger
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
class ArticleListFragment : Fragment(), Logger {

    private lateinit var binding: FragmentArticleListBinding

    private val articleViewModel: ArticleListViewModel by viewModels {
        ArticleListViewModel.Factory(App.articleRepository)
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

        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchQuery ->
                    articleViewModel.searchArticles(searchQuery)
                }
                return true
            }
        }

        binding.searchView?.setOnQueryTextListener(queryTextListener)

        articleViewModel.state.observe(viewLifecycleOwner) { state ->
            logDebug("ArticleViewModel state: ${state.javaClass.simpleName}")
            when (state) {
                is State.Loading -> binding.loadingView.root.visibility = View.VISIBLE
                is State.Ready -> handleArticles(state.result)
                is State.Error -> {
                    binding.loadingView.root.visibility = View.GONE
                    activity?.toast(state.error)
                }
            }
        }

    }

    private fun handleArticles(result: Success<List<Article>>) {
        val articles = result.data
        logInfo("Sending article data to the ArticleListAdapter...")
        adapter.setArticleData(articles)
        binding.loadingView.root.visibility = View.GONE
        when (result) {
            is LocalSuccess -> {
                activity?.toast("Got some news from your database!")
                checkIsRefreshing()
            }
            is RemoteSuccess -> {
                activity?.toast("Updated your news database with the latest news!")
                checkIsRefreshing()
            }
        }
    }

    private fun checkIsRefreshing() {
        if (articleRefreshLayout.isRefreshing) {
            articleRefreshLayout.isRefreshing = false
        }
    }

}