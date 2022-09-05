package com.danielpriddle.drpnews.interfaces

import com.danielpriddle.drpnews.models.Article

interface NewsService {
    fun getArticles(): ArrayList<Article?>
}