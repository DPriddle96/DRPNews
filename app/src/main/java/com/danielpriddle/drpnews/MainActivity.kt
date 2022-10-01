package com.danielpriddle.drpnews

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielpriddle.drpnews.databinding.ActivityMainBinding
import com.danielpriddle.drpnews.data.networking.buildApiService
import com.danielpriddle.drpnews.data.services.APINewsService
import com.danielpriddle.drpnews.utils.NetworkStatusChecker

class MainActivity : AppCompatActivity() {

    init {
        activity = this
    }

    //create a companion object to create a static newsService instance
    companion object {
        private var activity: MainActivity? = null

        /*
            9/26/2022 update: add a NetworkStatusChecker instance to the APINewsService constructor.
            This way, the service can make connection checks instead of the activity or fragment.
         */
        val newsService by lazy {
            APINewsService(buildApiService(),
                NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java)))
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding setup and display the view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
