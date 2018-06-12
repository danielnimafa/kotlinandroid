package com.loserba.android.utils.ext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.animation.Animation

/**
 * Created by danielnimafa on 04/04/18.
 */

fun View.animateFadeIn(duration: Long) {
    this.animate()
            .alpha(1.0f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    this@animateFadeIn.visibility = View.VISIBLE
                }
            })
}

fun View.animateFadeOut(duration: Long) {
    this.animate()
            .alpha(0.0f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    this@animateFadeOut.visibility = View.GONE
                }
            })
}

fun Animation.onAnimationEnd(onAnimationEnd: (Animation) -> Unit) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            animation?.also { onAnimationEnd.invoke(it) }
        }

        override fun onAnimationStart(animation: Animation?) {}
    })
}