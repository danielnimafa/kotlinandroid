package com.danielnimafa.android.androidkotlinapp.utils.networking

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ServiceApi {

    @Multipart
    @POST("api/login")
    fun login(@Part("User[username]") username: RequestBody,
              @Part("User[password]") password: RequestBody): Observable<Response<ResponseBody>>

    @POST("api/logout")
    fun logout(): Observable<Response<ResponseBody>>

    @GET("data/list")
    fun pickupList(): Observable<Response<ResponseBody>>

    /*@Multipart
    @POST("data/submit")
    fun submitData(@Part("Data[taskId]") taskId: RequestBody,
                       @Part("Data[name]") signName: RequestBody,
                       @Part("Data[codes]") palletCodes: RequestBody,
                       @Part picture: MultipartBody.Part): Observable<Response<CommonBody>>*/
}