package com.arivero007.beerappandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.adapters.BeersAdapter
import com.arivero007.beerappandroid.utils.webservice.Beer
import com.arivero007.beerappandroid.utils.webservice.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeersListActivity : AppCompatActivity() {

    private val TAG = "BeerListActivity: "

    private var beersModel: BeersListViewModel? = null
    private lateinit var beers: List<Beer>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    //LifeCycle

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

    //REST
    fun downloadListOfBeers(){

        val retrofit = RetrofitBuilder()

        retrofit.apiService.getListOfBeers().enqueue(
            object: Callback<List<Beer>>{
                override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                    val res = response.body()
                    if (res != null){
                        beers = res
                        beersModel?.setBeers(beers)
                        setUpRecyclerView()
                    }
                }

                override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    //RecyclerView

    fun setUpRecyclerView(){
        recyclerView = beers_view
        viewManager = LinearLayoutManager(this)
        viewAdapter = BeersAdapter(beers)
        recyclerView.layoutManager = viewManager
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20);
        recyclerView.adapter = viewAdapter
    }
}