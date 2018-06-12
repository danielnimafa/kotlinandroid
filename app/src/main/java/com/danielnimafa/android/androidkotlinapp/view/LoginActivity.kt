package com.danielnimafa.android.androidkotlinapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.danielnimafa.android.androidkotlinapp.R
import com.danielnimafa.android.androidkotlinapp.presenter.LoginPresenter
import com.danielnimafa.android.androidkotlinapp.view.iface.LoginView
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import io.reactivex.disposables.CompositeDisposable

class LoginActivity : MvpActivity<LoginView, LoginPresenter>(), LoginView {

    companion object {
        operator fun get(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun createPresenter(): LoginPresenter = LoginPresenter(CompositeDisposable())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun showMessage(message: String, title: String?, type: Int) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}
