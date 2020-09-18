package com.arivero007.beerappandroid.utils.webservice

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("beers")
    fun getListOfBeers(): Call<List<Beer>>
}