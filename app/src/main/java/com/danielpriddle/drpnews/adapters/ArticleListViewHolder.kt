package com.danielpriddle.drpnews.adapters

import androidx.recyclerview.widget.RecyclerView
import com.danielpriddle.drpnews.databinding.ArticleListViewHolderBinding

/**
 * ArticleListViewHolder
 *
 * This ViewHolder class is used to define a view to display Article properties in the RecyclerView.
 * In this case, it defines two TextViews, one to display the name of the source of the Article,
 * and another to display the title of the Article.
 * @author Dan Priddle
 */
class ArticleListViewHolder(binding: ArticleListViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var articleTitleTextView = binding.articleTitle
    var articleSourceTextView = binding.articleSource
}