package com.danielpriddle.drpnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielpriddle.drpnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding setup and display the view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
