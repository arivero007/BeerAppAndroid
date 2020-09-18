package com.arivero007.beerappandroid.utils.webservice

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Beer(

    @SerializedName("name")
    var name: String,
    @SerializedName("image_url")
    var image_url: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("abv")
    var abv: Double

): Serializable