package com.msf.mydog.net

import android.content.Context
import com.google.gson.GsonBuilder
import com.msf.mydog.BuildConfig
import com.msf.mydog.util.NetworkUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebServiceManager {

    private var auth = false
    private var baseUrl = BuildConfig.API_URL

    private var token: String? = null

    fun getinstance(auth: Boolean, context: Context, token: String? = null): Services {
        this.auth = auth
        this.token = token
        return WebServiceManager.retrofit(context).create(Services::class.java)
    }

    private fun retrofit(context: Context): Retrofit {

        val client = OkHttpClient.Builder()
        client.readTimeout(60, TimeUnit.SECONDS)
        client.connectTimeout(15, TimeUnit.SECONDS)

        client.addInterceptor { chain ->

            if (!NetworkUtil.isOnline(context)) {
                throw Exception("No network detected")
            }

            return@addInterceptor chain.proceed(chain.request())
        }


        if (auth) {

            client.addInterceptor { chain ->

                val original = chain.request()
                val newBuilder = original.newBuilder()


                token?.let { key ->
                    val requestOri = newBuilder
                            .header("Content-Type", "application/json")
                            .header("Authorization", key)
                            .method(original.method(), original.body())
                            .build()

                    return@addInterceptor chain.proceed(requestOri)
                } ?: run {
                    throw SecurityException("Não foi token")
                }

            }
        }


        return Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(client.build())
                .build()
    }
}