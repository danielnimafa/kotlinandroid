package com.danielnimafa.android.androidkotlinapp.presenter

import com.danielnimafa.android.androidkotlinapp.utils.networking.ClientRequest
import com.danielnimafa.android.androidkotlinapp.view.iface.LoginView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm

class LoginPresenter(private val subs: CompositeDisposable) : MvpBasePresenter<LoginView>() {

    private lateinit var realm: Realm

    fun initInstance() {
        realm = Realm.getDefaultInstance()
    }

    fun clearInstance() {
        subs.clear()
        realm.close()
    }

    fun postLoginSubmit() {
        subs.add(ClientRequest.loginRequest("deviceId", "username", "password",
                success = {

                },
                fail = {

                },
                error = {

                }))
    }
}