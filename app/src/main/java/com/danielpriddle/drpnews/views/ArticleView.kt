package com.danielpriddle.drpnews.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.danielpriddle.drpnews.databinding.ArticleViewBinding
import com.danielpriddle.drpnews.models.Article

class ArticleView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: ArticleViewBinding

    fun setArticle(article: Article) {
        setSourceName(article.source.name)
        setArticleTitle(article.title)
        setArticleAuthor(article.author)
        setArticleDescription(article.description)
        setArticleUrl(article.url)
        setArticlePublishedAt(article.publishedAt)
    }

    private fun setSourceName(sourceName: String) {
        binding.sourceNameTextView.text = sourceName
        binding.sourceNameTextView.visibility = if (sourceName.isEmpty()) GONE else VISIBLE
    }

    private fun setArticleTitle(title: String) {
        binding.articleTitleTextView.text = title
        binding.articleTitleTextView.visibility = if (title.isEmpty()) GONE else VISIBLE
    }

    private fun setArticleAuthor(author: String?) {
        binding.articleAuthorTextView.text = "By $author"
        binding.articleAuthorTextView.visibility = if (author.isNullOrEmpty()) GONE else VISIBLE
    }

    private fun setArticleDescription(description: String?) {
        binding.articleDescriptionTextView.text = description
        binding.articleDescriptionTextView.visibility =
            if (description.isNullOrEmpty()) GONE else VISIBLE
    }

    private fun setArticleUrl(url: String) {
        binding.articleUrlTextView.text = url
        binding.articleUrlTextView.visibility = if (url.isEmpty()) GONE else VISIBLE
    }

    private fun setArticlePublishedAt(publishedAt: String) {
        binding.articlePublishedAtTextView.text = publishedAt
        binding.articlePublishedAtTextView.visibility = if (publishedAt.isEmpty()) GONE else VISIBLE
    }

    init {
        this.binding = ArticleViewBinding.inflate(LayoutInflater.from(context), this)
    }
}