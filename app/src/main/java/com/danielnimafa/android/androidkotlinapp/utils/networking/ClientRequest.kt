package com.danielnimafa.android.androidkotlinapp.utils.networking

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class ApiRequestHandler() {

    fun <T> doSubscribe(observable: Observable<Response<T>>,
                        completion: (T) -> Unit,
                        fail: ((ResponseBody) -> Unit)? = null,
                        error: ((Throwable) -> Unit)? = null): DisposableObserver<Response<T>> {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observeRequest({ completion(it) }, { fail?.invoke(it) }, { error?.invoke(it) }))
    }

    private fun <T> observeRequest(completion: (T) -> Unit,
                                   failure: (ResponseBody) -> Unit,
                                   error: (Throwable) -> Unit): DisposableObserver<Response<T>> {
        return object : DisposableObserver<Response<T>>() {

            var obj: T? = null
            var failBody: ResponseBody? = null

            override fun onComplete() {
                obj?.let { completion(it) } ?: run { failure(failBody!!) }
            }

            override fun onNext(t: Response<T>) {
                if (t.isSuccessful) obj = t.body() else failBody = t.errorBody()
            }

            override fun onError(e: Throwable) {
                error(e)
            }
        }
    }

}

/*
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
}*/
