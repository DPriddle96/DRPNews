package com.danielpriddle.drpnews.utils

import android.animation.ValueAnimator
import android.view.View

fun fadeIn(view: View) {
    view.alpha = 0f
    view.visibility = View.VISIBLE

    val alphaAnimator = ValueAnimator.ofFloat(0f, 1f)
    alphaAnimator.duration = 1000
    alphaAnimator.addUpdateListener {
        val animationAlpha = it.animatedValue as Float
        view.alpha = animationAlpha
    }
    alphaAnimator.start()
}