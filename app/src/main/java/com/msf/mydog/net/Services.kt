package com.msf.mydog.net

import com.msf.mydog.model.ResponseFeed
import com.msf.mydog.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.*

interface Services{

    @FormUrlEncoded
    @POST("/signup")
    fun signup(@Field("email")email: String) : Call<ResponseLogin>

    @GET("/feed")
    fun getFeed(@Query("category") category: String = "husky") : Call<ResponseFeed>
}