package com.danielnimafa.android.androidkotlinapp.presenter

import com.danielnimafa.android.androidkotlinapp.utils.ext.createRequestBody
import com.danielnimafa.android.androidkotlinapp.utils.networking.ApiRequestHandler
import com.danielnimafa.android.androidkotlinapp.utils.networking.ServiceGenerator
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

    fun postLoginSubmit(uname: String, pass: String) {
        val rbUname = createRequestBody("multipart/form-data", uname)
        val rbPass = createRequestBody("multipart/form-data", pass)

        val loginTask = ServiceGenerator.createService("deviceId").login(rbUname, rbPass)
        val request = ApiRequestHandler()

        subs.add(request.doSubscribe(observable = loginTask,
                completion = {

                },
                fail = {

                },
                error = {

                }))
    }
}