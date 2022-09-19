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
import com.danielpriddle.drpnews.models.Article
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

    private val adapter by lazy { ArticleListAdapter(::onArticleSelected) }
    private lateinit var articleRefreshLayout: SwipeRefreshLayout

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
        articleRefreshLayout = binding.articleRefreshLayout
        articleRefreshLayout.setOnRefreshListener {
            articleRefreshLayout.isRefreshing = true
            getArticles()
        }
        binding.articleListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.articleListRecyclerView.adapter = adapter
        getArticles()

    }

    private fun onArticleSelected(article: Article) {
        val action = ArticleListFragmentDirections.actionListToArticle(article)
        findNavController().navigate(action)
    }

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