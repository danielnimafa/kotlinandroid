package com.danielnimafa.android.androidkotlinapp.view.iface

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by dioilham on 10/19/17.
 */

interface ProgressView : MvpView {
    fun showProgress()
    fun hideProgress()
}