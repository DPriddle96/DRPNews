package com.danielpriddle.drpnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SwitchCompat
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.databinding.ActivityMainBinding
import com.danielpriddle.drpnews.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding setup and display the view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val switchItem = menu!!.findItem(R.id.wifi_switch_item)
        val switch = switchItem.actionView.findViewById<SwitchCompat>(R.id.wifi_switch)
        viewModel.isDownloadOverWifiOnly.observe(this) { isDownloadOverWifiOnly ->
            switch.isChecked = isDownloadOverWifiOnly
            if (isDownloadOverWifiOnly) {
                switch.text = getString(R.string.switch_on, switchItem.title)
            } else {
                switch.text = getString(R.string.switch_off, switchItem.title)
            }
        }
        switch.setOnCheckedChangeListener { _, _ ->
            viewModel.toggleDownloadOverWifiOnly()
        }
        return true
    }

}
