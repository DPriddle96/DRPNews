package com.danielpriddle.drpnews.interfaces

import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.networking.APIResult

/**
 * NewsService
 *
 * This interface defines a contract to classes that implement it.
 * For now, it defines a getArticles function that any class that implements this interface
 * must implement.
 * @author Dan Priddle
 */
interface NewsService {
    fun getTopHeadlines(onArticlesReceived: (APIResult<List<Article>>) -> Unit)
}