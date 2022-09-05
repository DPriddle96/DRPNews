package com.danielpriddle.drpnews.services

import com.danielpriddle.drpnews.interfaces.NewsService
import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.models.Source

/**
 * InMemoryNewsService
 *
 * This service class implements the NewsService interface and helps keep data retrieval concerns
 * separate from UI logic
 * @see NewsService
 * @author Dan Priddle
 */
class InMemoryNewsService : NewsService {
    /**
     * getArticles
     *
     * This function overrides the getArticles function defined in the NewsService interface by
     * retrieving hardcoded Articles stored in memory.
     * @return A nullable ArrayList of Article objects
     */
    override fun getArticles(): ArrayList<Article?> {
        return arrayListOf(
            Article(
                Source(name = "The Onion"),
                title = "Trump Claims Seized Classified Documents Had Been In His Family For Generations",
                url = "https://www.theonion.com/trump-claims-seized-classified-documents-had-been-in-hi-1849462609",
                description = "Stating he was “absolutely sickened” over the loss of “such precious heirlooms,” former President Donald Trump claimed Friday that the classified documents seized in an FBI raid had been in his family for generations.",
                publishedAt = "8/26/2022 12:50PM"
            ),
            Article(
                Source(name = "BBC Sport"),
                title = "Belgian Grand Prix: No stopping 'phenomenal' Verstappen as second title approaches",
                author = "Andrew Benson",
                description = "There is just no stopping Max Verstappen right now - not on his way to a stunning victory in the Belgian Grand Prix, and not as he heads inexorably towards a second consecutive world championship.",
                url = "https://www.bbc.com/sport/formula1/62707533",
                publishedAt = "8/28/2022"
            ),
            Article(
                Source(name = "BBC News"),
                title = "California to ban sales of petrol-only vehicles by 2035",
                author = "Annabelle Liang",
                description = "California is to ban the sale of new petrol-only vehicles by 2035, marking a historic step in the state's attempts to tackle climate change.",
                url = "https://www.bbc.com/news/business-62683260",
                publishedAt = "8/26/2022"
            ),
            Article(
                Source(name = "BBC News"),
                title = "Jerome Powell: US stock markets down after interest rate warning",
                author = "Michelle Fleury & Alys Davies",
                description = "Stock markets in the US ended the week sharply down following tough comments by the head of the country's central bank, the Federal Reserve.",
                url = "https://www.bbc.com/news/business-62695937",
                publishedAt = "8/27/2022"
            ),
            Article(
                Source(name = "The Onion"),
                title = "LeBron James Declares Re-Signing With Lakers Gives Him Best Chance To Miss Playoffs",
                url = "https://www.theonion.com/lebron-james-declares-re-signing-with-lakers-gives-him-1849461932",
                publishedAt = "8/26/2022"
            ),
            null
        )
    }
}