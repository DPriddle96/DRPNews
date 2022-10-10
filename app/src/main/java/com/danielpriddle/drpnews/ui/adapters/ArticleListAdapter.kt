package com.danielpriddle.drpnews.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielpriddle.drpnews.databinding.ArticleListViewHolderBinding
import com.danielpriddle.drpnews.data.database.entities.relations.ArticleAndSource
import com.danielpriddle.drpnews.data.models.Article

/**
 * ArticleDataManager
 *
 * This Adapter class binds Article data to the views described in the RecyclerView's ViewHolder. It
 * also defines a click listener interface for other classes to implement to respond to the user
 * clicking on an Article in the RecyclerView
 * @author Dan Priddle
 */
class ArticleListAdapter(
    private val clickListener: (Article) -> Unit,
) : RecyclerView.Adapter<ArticleListViewHolder>() {

    //update 9/19/2022: Move this outside of parameters and make it mutable to help with refresh
    private val articles: MutableList<Article> = mutableListOf()

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
        holder.itemView.setOnClickListener {
            clickListener(articles[position])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    /**
     * setArticleData
     *
     * This function takes in a list of Articles that have been retrieved by the API and populates
     * the 'articles' mutableList. This helps to decouple data population from the initialization of
     * the adapter.
     * @param articles An Article list passed in after retrieving API data
     * @return NONE
     */
    fun setArticleData(articles: List<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }
}