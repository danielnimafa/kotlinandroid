package com.danielnimafa.android.androidkotlinapp.utils.networking

import com.danielnimafa.android.androidkotlinapp.utils.Const
import com.danielnimafa.android.androidkotlinapp.utils.PrefHelper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by danielnimafa on 04/04/18.
 */

object ServiceGenerator {

    fun createService(deviceID: String, auth: String): ServiceApi {
        val token = "Bearer $auth"
        val builder = Retrofit.Builder()
                .baseUrl(PrefHelper.loadBaseURL())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(null)
        deviceID?.also {
            httpClient.addInterceptor { chain ->
                var original: Request = chain.request()
                var requestBuilder = original.newBuilder()
                        .header(Const.header_auth, token)
                        .header(Const.header_appkey, Const.value_header_appkey)
                        .header(Const.header_appsecret, Const.value_header_appsecret)
                        .header(Const.header_deviceID, it)
                        .method(original.method(), original.body())
                var request = requestBuilder.build()
                chain.proceed(request)
            }
        }
        if (PrefHelper.getDevMode() == 1) addLoggingInterceptor(httpClient)
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(ServiceApi::class.java)
    }

    fun createService(deviceID: String): ServiceApi {

        val builder = Retrofit.Builder()
                .baseUrl(PrefHelper.loadBaseURL())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(null)
        deviceID?.also {
            httpClient.addInterceptor { chain ->
                var original: Request = chain.request()
                var requestBuilder = original.newBuilder()
                        .header(Const.header_appkey, Const.value_header_appkey)
                        .header(Const.header_appsecret, Const.value_header_appsecret)
                        .header(Const.header_deviceID, it)
                        .method(original.method(), original.body())
                var request = requestBuilder.build()
                chain.proceed(request)
            }
        }
        if (PrefHelper.getDevMode() == 1) addLoggingInterceptor(httpClient)
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(ServiceApi::class.java)
    }

    fun <S> createService(serviceClass: Class<S>): S {

        val httpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(null)

        val builder = Retrofit.Builder()
                .baseUrl("URL")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        if (PrefHelper.getDevMode() == 1) addLoggingInterceptor(httpClient)
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

    private fun addLoggingInterceptor(httpClient: OkHttpClient.Builder) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }
}