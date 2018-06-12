package com.danielnimafa.android.androidkotlinapp.view.iface

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by admin on 14/11/17.
 */
interface ShowListDataView : MvpView {
    fun showList(list: List<Any>)
}