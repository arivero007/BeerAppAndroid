package com.arivero007.beerappandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.utils.webservice.Beer
import com.arivero007.beerappandroid.utils.webservice.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeersListActivity : AppCompatActivity() {

    private val TAG = "BeerListActivity: "

    private var beersModel: BeersListViewModel? = null
    private lateinit var beers: List<Beer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beersModel = ViewModelProvider(this).get(BeersListViewModel::class.java)
        downloadListOfBeers()

    }

    override fun onResume() {
        super.onResume()

        //Listen for model
        beersModel!!.getBeers().observe(this, Observer<List<Beer>>{
            if (it != null){
                beers = it
            }
        })
    }

    fun downloadListOfBeers(){

        val retrofit = RetrofitBuilder()

        retrofit.apiService.getListOfBeers().enqueue(
            object: Callback<List<Beer>>{
                override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                    val res = response.body()
                    if (res != null){
                        beers = res
                        beersModel?.setBeers(beers)
                    }
                }

                override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}