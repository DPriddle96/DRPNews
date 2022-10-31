package com.danielpriddle.drpnews.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.danielpriddle.drpnews.data.models.*
import com.danielpriddle.drpnews.databinding.FragmentArticleListBinding
import com.danielpriddle.drpnews.ui.composables.ArticleListComposable
import com.danielpriddle.drpnews.utils.*
import com.danielpriddle.drpnews.viewmodels.ArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint

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
@AndroidEntryPoint
class ArticleListFragment : Fragment(), Logger {

    private lateinit var binding: FragmentArticleListBinding

    private val articleViewModel: ArticleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        fadeIn(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        binding.searchView.setOnQueryTextListener(queryTextListener)

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
        binding.composeView.setContent {
            MaterialTheme {
                ArticleListComposable(
                    articles,
                    clickListener = { article ->
                        val action = ArticleListFragmentDirections.actionListToArticle(article)
                        findNavController().navigate(action)
                    }
                )
            }
        }
        binding.loadingView.root.visibility = View.GONE
        when (result) {
            is LocalSuccess -> {
                activity?.toast("Got some news from your database!")
            }
            is RemoteSuccess -> {
                activity?.toast("Updated your news database with the latest news!")
            }
        }
    }
}