package com.arivero007.beerappandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.utils.webservice.Beer

class BeersListActivity : AppCompatActivity() {

    private lateinit var beersModel: BeersListViewModel
    private lateinit var beers: List<Beer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Listen for model
        beersModel.getBeers().observe(this, Observer<List<Beer>>{
            if (it != null){
                beers = it
            }
        })
    }


}