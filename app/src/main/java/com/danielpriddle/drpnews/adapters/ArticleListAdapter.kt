package com.danielpriddle.drpnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielpriddle.drpnews.databinding.ArticleListViewHolderBinding
import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.views.ArticleListViewHolder

/**
 * ArticleDataManager
 *
 * This Adapter class binds Article data to the views described in the RecyclerView's ViewHolder. It
 * also defines a click listener interface for other classes to implement to respond to the user
 * clicking on an Article in the RecyclerView
 * @author Dan Priddle
 */
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
        //bind the Article properties to the TextViews in the ViewHolder
        holder.articleTitleTextView.text = articles[position].title
        holder.articleSourceTextView.text = articles[position].source.name

        //set up a click listener!
        holder.itemView.setOnClickListener { view ->
            clickListener.articleClicked(articles[position], view)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}