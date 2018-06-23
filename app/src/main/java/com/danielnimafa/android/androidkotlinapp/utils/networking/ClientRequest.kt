package com.danielnimafa.android.androidkotlinapp.utils.networking

import com.danielnimafa.android.androidkotlinapp.utils.Sout
import com.danielnimafa.android.androidkotlinapp.utils.ext.createRequestBody
import com.danielnimafa.android.androidkotlinapp.utils.networking.ServiceGenerator.createService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

object ClientRequest {

    fun loginRequest(devid: String, uname: String, pass: String,
                     success: (ResponseBody?) -> Unit,
                     fail: (String?) -> Unit,
                     error: (Throwable) -> Unit): DisposableObserver<Response<ResponseBody>> {
        val rbUname = createRequestBody("multipart/form-data", uname)
        val rbPass = createRequestBody("multipart/form-data", pass)
        return createService(devid).login(rbUname, rbPass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Response<ResponseBody>>() {

                    override fun onNext(t: Response<ResponseBody>) {
                        if (t.isSuccessful) success(t.body()) else fail(t.errorBody()?.string())
                    }

                    override fun onError(e: Throwable) {
                        Sout.trace(e as Exception)
                        error(e)
                    }

                    override fun onComplete() {}
                })
    }

    fun logout(imei: String, auth: String, success: (ResponseBody?) -> Unit, fail: (String?) -> Unit, error: (Throwable) -> Unit): Disposable {
        return createService(imei, auth).logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Response<ResponseBody>>() {
                    override fun onNext(t: Response<ResponseBody>) {
                        if (t.isSuccessful) success(t.body()) else fail(t.errorBody()?.string())

                    }

                    override fun onComplete() {}

                    override fun onError(e: Throwable) {
                        Sout.trace(e as Exception)
                        error(e)
                    }
                })

    }
}