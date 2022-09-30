package com.danielpriddle.drpnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.databinding.FragmentArticleDetailBinding
import com.danielpriddle.drpnews.data.models.Article

/**
 * ArticleDetailFragment
 *
 * This Fragment displays detailed information about Articles abd is created when an Article is
 * clicked in the RecyclerView displayed by the ArticleListFragment. It takes in an Article as an
 * argument and displays a custom ArticleView.
 * @author Dan Priddle
 */
class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val safeArgs = ArticleDetailFragmentArgs.fromBundle(it)
            article = safeArgs.article
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)

        setSourceName(article.source.name)
        setArticleTitle(article.title)
        setArticleAuthor(article.author)
        setArticleDescription(article.description)
        setArticleUrl(article.url)
        setArticleContent(article.content)
        setArticlePublishedAt(article.publishedAt)
        setArticleImage(article.urlToImage)

        return binding.root
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
        binding.sourceNameTextView.visibility =
            if (sourceName.isEmpty()) LinearLayout.GONE else LinearLayout.VISIBLE
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
        binding.articleTitleTextView.visibility =
            if (title.isEmpty()) LinearLayout.GONE else LinearLayout.VISIBLE
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
        binding.articleAuthorTextView.visibility =
            if (author.isNullOrEmpty()) LinearLayout.GONE else LinearLayout.VISIBLE
    }

    /**
     * setArticleImage
     *
     * This function uses Glide to get the image from the passed in URL and display it in the
     * articleImageView
     * @param urlToImage The urlToImage property of the Article object passed into the setArticle function.
     * @return NONE
     */
    private fun setArticleImage(urlToImage: String?) {
        if (!urlToImage.isNullOrEmpty()) {
            Glide.with(this).load(urlToImage).into(binding.articleImageView)
        } else {
            //need this, otherwise UI doesn't paint
            binding.articleImageView.visibility = LinearLayout.GONE
        }

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
            if (description.isNullOrEmpty()) LinearLayout.GONE else LinearLayout.VISIBLE
    }

    /**
     * setArticleContent
     *
     * This function sets the text property of the articleContentTextView and hides the TextView
     * if the passed in string is null or empty to make the layout cleaner.
     * @param content The content property of the Article object passed into the setArticle function.
     * @return NONE
     */
    private fun setArticleContent(content: String?) {
        binding.articleContentTextView.text = content
        binding.articleContentTextView.visibility =
            if (content.isNullOrEmpty()) LinearLayout.GONE else LinearLayout.VISIBLE
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
        binding.articleUrlTextView.visibility =
            if (url.isEmpty()) LinearLayout.GONE else LinearLayout.VISIBLE
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
        binding.articlePublishedAtTextView.visibility =
            if (publishedAt.isEmpty()) LinearLayout.GONE else LinearLayout.VISIBLE
    }


}