package com.danielpriddle.drpnews.services

import com.danielpriddle.drpnews.interfaces.NewsService
import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.models.NewsAPIResponse
import com.danielpriddle.drpnews.networking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * APINewsService
 *
 * This service class implements the NewsService interface and helps keep data retrieval concerns
 * separate from UI logic
 * @see NewsService
 * @author Dan Priddle
 */
class APINewsService(private val api: NewsAPI) : NewsService {

    override fun getTopHeadlines(onArticlesReceived: (APIResult<List<Article>>) -> Unit) {
        api.getTopHeadlines(API_KEY, "us").enqueue(object : Callback<NewsAPIResponse> {
            override fun onFailure(call: Call<NewsAPIResponse>, error: Throwable) {
                onArticlesReceived(Failure(error))
            }

            override fun onResponse(
                call: Call<NewsAPIResponse>,
                response: Response<NewsAPIResponse>,
            ) {
                val articles = response.body()?.articles
                if (articles == null) {
                    onArticlesReceived(Failure(NullPointerException("Hmm... no articles were retrieved by your request")))
                    return
                }
                onArticlesReceived(Success(articles))
            }
        })
    }
}