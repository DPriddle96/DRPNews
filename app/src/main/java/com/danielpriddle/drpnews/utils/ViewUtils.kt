package com.danielpriddle.drpnews.utils

import android.content.Context
import android.widget.Toast

/**
 * Function to help simplify the creation of Toast messages. Was used in
 * [Android Networking: Beyond the Basics](https://www.raywenderlich.com/35150454-android-networking-beyond-the-basics/)
 * and really liked having to only pass 1 parameter instead of 3 :)
 */
fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}