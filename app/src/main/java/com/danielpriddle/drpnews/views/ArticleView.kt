package com.danielpriddle.drpnews.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.databinding.ArticleViewBinding
import com.danielpriddle.drpnews.models.Article

/**
 * ArticleView
 *
 * This custom view class defines a LinearLayout for displaying Article properties
 * @author Dan Priddle
 */
class ArticleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    //inflate the view
    private val binding = ArticleViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = VERTICAL
    }

    /**
     * setArticle
     *
     * This function acts as a parent function that helps to facilitate the construction of the
     * custom ArticleView by parsing out Article properties and passing them to individual
     * private functions defined in this class.
     * @param article An Article object passed in from the MainActivity
     * @return NONE
     */
    fun setArticle(article: Article) {
        setSourceName(article.source.name)
        setArticleTitle(article.title)
        setArticleAuthor(article.author)
        setArticleDescription(article.description)
        setArticleUrl(article.url)
        setArticlePublishedAt(article.publishedAt)
    }

    /**
     * setSourceName
     *
     * This function sets the text property of the sourceNameTextView and hides the TextView if the
     * passed in string is empty to make the layout cleaner.
     * @param sourceName The name property of the Source defined in the Article object passed into
     * the setArticle function.
     * @return NONE
     */
    private fun setSourceName(sourceName: String) {
        binding.sourceNameTextView.text = sourceName
        binding.sourceNameTextView.visibility = if (sourceName.isEmpty()) GONE else VISIBLE
    }

    /**
     * setArticleTitle
     *
     * This function sets the text property of the articleTitleTextView and hides the TextView if
     * the passed in string is empty to make the layout cleaner.
     * @param title The title property of the Article object passed into the setArticle function.
     * @return NONE
     */
    private fun setArticleTitle(title: String) {
        binding.articleTitleTextView.text = title
        binding.articleTitleTextView.visibility = if (title.isEmpty()) GONE else VISIBLE
    }

    /**
     * setArticleAuthor
     *
     * This function sets the text property of the articleAuthorTextView and hides the TextView if
     * the passed in string is null or empty to make the layout cleaner.
     * @param author The author property of the Article object passed into the setArticle function.
     * @return NONE
     */
    private fun setArticleAuthor(author: String?) {
        binding.articleAuthorTextView.text = resources.getString(R.string.article_by, author)
        binding.articleAuthorTextView.visibility = if (author.isNullOrEmpty()) GONE else VISIBLE
    }

    /**
     * setArticleDescription
     *
     * This function sets the text property of the articleDescriptionTextView and hides the TextView
     * if the passed in string is null or empty to make the layout cleaner.
     * @param description The description property of the Article object passed into the setArticle function.
     * @return NONE
     */
    private fun setArticleDescription(description: String?) {
        binding.articleDescriptionTextView.text = description
        binding.articleDescriptionTextView.visibility =
            if (description.isNullOrEmpty()) GONE else VISIBLE
    }

    /**
     * setArticleUrl
     *
     * This function sets the text property of the articleUrlTextView and hides the TextView if
     * the passed in string is empty to make the layout cleaner.
     * @param url The description property of the Article object passed into the setArticle function.
     * @return NONE
     */
    private fun setArticleUrl(url: String) {
        binding.articleUrlTextView.text = url
        binding.articleUrlTextView.visibility = if (url.isEmpty()) GONE else VISIBLE
    }

    /**
     * setArticlePublishedAt
     *
     * This function sets the text property of the articlePublishedAtTextView and hides the TextView if
     * the passed in string is empty to make the layout cleaner.
     * @param publishedAt The publishedAt property of the Article object passed into the setArticle function.
     * @return NONE
     */
    private fun setArticlePublishedAt(publishedAt: String) {
        binding.articlePublishedAtTextView.text = publishedAt
        binding.articlePublishedAtTextView.visibility = if (publishedAt.isEmpty()) GONE else VISIBLE
    }


}