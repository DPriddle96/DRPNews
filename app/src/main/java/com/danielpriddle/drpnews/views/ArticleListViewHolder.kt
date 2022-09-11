package com.danielpriddle.drpnews.views

import androidx.recyclerview.widget.RecyclerView
import com.danielpriddle.drpnews.databinding.ArticleListViewHolderBinding

class ArticleListViewHolder(binding: ArticleListViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var articleTitleTextView = binding.articleTitle
    var articleSourceTextView = binding.articleSource
}