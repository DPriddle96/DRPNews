package com.danielpriddle.drpnews.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import com.danielpriddle.drpnews.databinding.ActivityMainBinding
import com.danielpriddle.drpnews.models.Article
import com.danielpriddle.drpnews.models.Source

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //Immutable map of Article objects.
    private var articles = mapOf(
        0 to Article(
            Source(name = "The Onion"),
            title = "Trump Claims Seized Classified Documents Had Been In His Family For Generations",
            url = "https://www.theonion.com/trump-claims-seized-classified-documents-had-been-in-hi-1849462609",
            description = "Stating he was “absolutely sickened” over the loss of “such precious heirlooms,” former President Donald Trump claimed Friday that the classified documents seized in an FBI raid had been in his family for generations.",
            publishedAt = "8/26/2022 12:50PM"
        ),
        1 to Article(
            Source(name = "BBC Sport"),
            title = "Belgian Grand Prix: No stopping 'phenomenal' Verstappen as second title approaches",
            author = "Andrew Benson",
            description = "There is just no stopping Max Verstappen right now - not on his way to a stunning victory in the Belgian Grand Prix, and not as he heads inexorably towards a second consecutive world championship.",
            url = "https://www.bbc.com/sport/formula1/62707533",
            publishedAt = "8/28/2022"
        ),
        2 to Article(
            Source(name = "BBC News"),
            title = "California to ban sales of petrol-only vehicles by 2035",
            author = "Annabelle Liang",
            description = "California is to ban the sale of new petrol-only vehicles by 2035, marking a historic step in the state's attempts to tackle climate change.",
            url = "https://www.bbc.com/news/business-62683260",
            publishedAt = "8/26/2022"
        ),
        3 to Article(
            Source(name = "BBC News"),
            title = "Jerome Powell: US stock markets down after interest rate warning",
            author = "Michelle Fleury & Alys Davies",
            description = "Stock markets in the US ended the week sharply down following tough comments by the head of the country's central bank, the Federal Reserve.",
            url = "https://www.bbc.com/news/business-62695937",
            publishedAt = "8/27/2022"
        ),
        4 to Article(
            Source(name = "The Onion"),
            title = "LeBron James Declares Re-Signing With Lakers Gives Him Best Chance To Miss Playoffs",
            url = "https://www.theonion.com/lebron-james-declares-re-signing-with-lakers-gives-him-1849461932",
            publishedAt = "8/26/2022"
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding setup and display the view
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //call user-made function to populate the 5 TextViews
        populateTextViews()
    }

    /**
     * populateTextViews
     * Purpose: This function is used to populate the 5 TextViews with content from the Article map.
     * It gets the list of TextViews from the article ViewGroup, loops through the Article map and
     * populates each TextView's text with the Article content returned from the getContent()
     * function.
     * Parameters: NONE
     * Returns: NONE
     */
    private fun populateTextViews() {
        //NEW WAY UPDATED 9/1 - Loops through Article map
        val textViews = binding.articleViewGroup.children.toList().filterIsInstance<TextView>()
        articles.forEach { entry ->
            val content = getContent(entry.key)
            textViews[entry.key].text = content
        }

        //OLD WAY - Loops through ViewGroup's children
//        articleViewGroup.children.forEach { textView ->
//            if (textView is TextView) {
//                val content = getContent(count)
//                textView.text = content
//                count++
//            }
//        }
    }

    /**
     * getContent
     * Purpose: This function is used to retrieve content from the Article map to be displayed in the TextViews.
     * It takes in an Int parameter 'key', which is the key from which to retrieve the Article value from.
     * It parses out the properties from the retrieved Article, checks for null and empty property values,
     * and concatenates them into a String variable 'content' and returns it.
     * Parameters: key: integer value representing the key in the Article map
     * Returns: A concatenated string of Article content
     */
    private fun getContent(key: Int): String {
        val title = articles[key]?.title
        val sourceName = articles[key]?.source?.name
        val author = articles[key]?.author
        val url = articles[key]?.url
        val publishedAt = articles[key]?.publishedAt
        val description = articles[key]?.description

        var content = "$sourceName \n$title \n"

        if (!author.isNullOrEmpty()) {
            content += "By $author on "
        }

        content += "$publishedAt \n"

        if (!description.isNullOrEmpty()) {
            content += "$description \n"
        }

        content += "$url \n"

        return content

    }
}