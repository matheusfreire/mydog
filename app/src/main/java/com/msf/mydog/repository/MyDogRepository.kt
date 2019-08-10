package com.msf.mydog.repository

import android.content.Context
import com.msf.mydog.model.ResponseFeed
import com.msf.mydog.model.ResponseLogin
import com.msf.mydog.model.User
import com.msf.mydog.net.Services
import com.msf.mydog.net.WebServiceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MyDogRepository {

    private fun getService(auth: Boolean, context: Context): Services {
        return WebServiceManager.getinstance(auth, context)
    }
    private fun getService(auth: Boolean, context: Context, token: String?): Services {
        return WebServiceManager.getinstance(auth, context, token)
    }

    fun signup(context: Context,email: String, onSuccess: (user: User?) -> Unit, onFailure: (message: String?) -> Unit) {
        getService(false, context).signup(email).enqueue(object : Callback<ResponseLogin?>{
            override fun onFailure(call: Call<ResponseLogin?>, t: Throwable) {
                onFailure(t.message)
            }

            override fun onResponse(call: Call<ResponseLogin?>, response: Response<ResponseLogin?>) {
                if(response.isSuccessful){
                    onSuccess(response.body()!!.user)
                } else {
                    onFailure("Erro ao fazer login")
                }
            }

        })
    }

    fun getFeed(context: Context,token: String?, dog: String, onSuccess: (list: List<String>?) -> Unit, onFailure: (message: String?) -> Unit) {
        getService(true, context, token).getFeed(dog).enqueue(object: Callback<ResponseFeed>{
            override fun onFailure(call: Call<ResponseFeed>, t: Throwable) {
                onFailure(t.message)

            }

            override fun onResponse(call: Call<ResponseFeed>, response: Response<ResponseFeed>) {
                if(response.isSuccessful){
                    onSuccess(response.body()!!.list)
                } else {
                    onFailure("Houve um erro")
                }
            }

        })

    }
}