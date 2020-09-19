package com.arivero007.beerappandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.utils.Utils
import com.arivero007.beerappandroid.utils.webservice.Beer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_beer.*

class BeerActivity : AppCompatActivity() {

    private lateinit var beer: Beer

    //LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        receiveData()
    }

    //Data Manager
    fun receiveData(){
        //Load data if information received
        if(intent.hasExtra("beer")){
            beer = intent.getSerializableExtra("beer") as Beer
            setBeerData()
        }else{
            Utils.showAlert(this, "No data received!")
        }
    }

    fun setBeerData() {
        beer_name.text = beer.name
        beer_abv.text = "ABV (Alcohol by Weight) = " + beer.abv
        beer_description.text = beer.description
        beer_description.movementMethod = ScrollingMovementMethod()
        Picasso.get().load(beer.image_url).into(beer_image)
    }

    //AppBar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}