package com.danielpriddle.drpnews.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danielpriddle.drpnews.databinding.FragmentArticleListBinding
import com.danielpriddle.drpnews.models.Article
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
class ArticleListFragment : Fragment(), ArticleListAdapter.ArticleListClickListener {

    private var listener: OnFragmentInteractionListener? = null
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
        binding.articleListRecyclerView.adapter = ArticleListAdapter(articles, this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            articleDataManager = ArticleDataManager(context)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onArticleClicked(article: Article, view: View)
    }

    override fun articleClicked(article: Article, view: View) {
        listener?.onArticleClicked(article, view)
    }
}