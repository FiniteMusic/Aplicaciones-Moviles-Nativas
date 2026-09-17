package com.marcudlcv.dolist.data.remote.api

import android.content.Context
import com.marcudlcv.dolist.data.local.TokenStorage
import com.marcudlcv.dolist.data.remote.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "http://10.0.2.2:5000/"

    fun create(
        context: Context,
        tokenStorage: TokenStorage
    ): ApiService {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                AuthInterceptor(tokenStorage)
            )
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}