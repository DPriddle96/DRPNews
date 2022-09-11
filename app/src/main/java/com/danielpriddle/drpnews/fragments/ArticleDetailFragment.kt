package com.danielpriddle.drpnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.databinding.FragmentArticleDetailBinding
import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.views.ArticleView

class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val safeArgs = ArticleDetailFragmentArgs.fromBundle(it)
            article = safeArgs.param1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)

        val articleView = ArticleView(container!!.context)
        articleView.setArticle(article)
        //NOTE: <merge> messes with orientation and adding parentTag didn't seem to work.
        //Is there a better way that I am missing?
        //articleView.orientation = LinearLayout.VERTICAL
        binding.articleViewGroup.addView(articleView)
        return binding.root
    }

    companion object {
        fun newInstance(): ArticleDetailFragment = ArticleDetailFragment()
    }

//    companion object {
//        private val ARG_LIST = "article"
//        fun newInstance(article: Article): ArticleDetailFragment {
//            val bundle = Bundle()
//            bundle.putParcelable(ARG_LIST, article)
//            val fragment = ArticleDetailFragment()
//            fragment.arguments = bundle
//            return fragment
//        }
//    }
}