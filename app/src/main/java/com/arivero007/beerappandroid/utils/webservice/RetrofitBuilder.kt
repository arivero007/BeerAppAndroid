package com.arivero007.beerappandroid.utils.webservice

import com.arivero007.beerappandroid.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    val apiService: ApiService

    init {
        apiService = getRetrofit().create(ApiService::class.java)
    }

    private fun getHttpLog(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .client(getHttpLog())
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

}