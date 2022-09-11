package com.danielpriddle.drpnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielpriddle.drpnews.databinding.ArticleListViewHolderBinding
import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.views.ArticleListViewHolder

class ArticleListAdapter(
    private val articles: List<Article>,
    private val clickListener: ArticleListClickListener,
) : RecyclerView.Adapter<ArticleListViewHolder>() {
    interface ArticleListClickListener {
        fun articleClicked(article: Article, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val binding =
            ArticleListViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.articleTitleTextView.text = articles[position].title
        holder.articleSourceTextView.text = articles[position].source.name
        holder.itemView.setOnClickListener { view ->
            clickListener.articleClicked(articles[position], view)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}