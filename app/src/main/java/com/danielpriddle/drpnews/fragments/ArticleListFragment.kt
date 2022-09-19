package com.danielpriddle.drpnews.fragments

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.danielpriddle.drpnews.MainActivity.Companion.newsService
import com.danielpriddle.drpnews.databinding.FragmentArticleListBinding
import com.danielpriddle.drpnews.adapters.ArticleListAdapter
import com.danielpriddle.drpnews.networking.Failure
import com.danielpriddle.drpnews.networking.NetworkStatusChecker
import com.danielpriddle.drpnews.networking.Success
import com.danielpriddle.drpnews.utils.toast

/**
 * ArticleListFragment
 *
 * This Fragment displays a list of Articles in a RecyclerView. It calls the NewsAPI to retrieve
 * Article data and passes that on to the ArticleListAdapter to bind the data to the
 * RecyclerView. It also contains a SwipeRefreshLayout that fetches updated data when the user
 * swipes down to refresh.
 * @author Dan Priddle
 */
class ArticleListFragment : Fragment() {

    private lateinit var binding: FragmentArticleListBinding

    //need a global instance of this since data population is now decoupled.
    private val adapter by lazy {
        ArticleListAdapter { article ->
            val action = ArticleListFragmentDirections.actionListToArticle(article)
            findNavController().navigate(action)
        }
    }

    //SwipeRefreshLayout instance to initialize later
    private lateinit var articleRefreshLayout: SwipeRefreshLayout

    //NetworkStatusChecker instance
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

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
            getArticles()
        }
        binding.articleListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.articleListRecyclerView.adapter = adapter
        //get initial article data from API
        getArticles()

    }

    /**
     * getArticles
     *
     * If the device is connected to the internet, this function calls a NewsAPI endpoint to get the
     * top stories and handles the callback when we get a response. If we successfully retrieve data,
     * we send it to the adapter (and hide the refresh spinner if this function was called from the
     * refreshListener).
     */
    private fun getArticles() {
        networkStatusChecker.performIfConnectedToInternet {
            newsService.getTopHeadlines { result ->
                when (result) {
                    is Success -> {
                        adapter.setArticleData(result.data)
                        if (articleRefreshLayout.isRefreshing) {
                            articleRefreshLayout.isRefreshing = false
                            activity?.toast("Got some breaking news for ya!")
                        }
                    }
                    is Failure -> {
                        result.error?.message?.let { activity?.toast(it) }
                    }
                }
            }
        }
    }

}