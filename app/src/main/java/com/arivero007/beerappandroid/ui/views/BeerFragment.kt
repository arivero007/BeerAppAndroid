package com.arivero007.beerappandroid.ui.views

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.ui.models.BeersListViewModel
import com.arivero007.beerappandroid.utils.webservice.Beer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_beer.*


class BeerFragment : Fragment() {

    private val beersModel: BeersListViewModel by activityViewModels()
    private lateinit var beer: Beer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(false)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beer, container, false)
    }

    override fun onResume() {
        super.onResume()

        beersModel.getSelectedBeer().observe(viewLifecycleOwner, Observer<Beer>{
            if(it != null){
                beer = it
                setBeerData()
            }
        })
    }

    fun setBeerData(){
        beer_name.text = beer.name
        beer_abv.text = "ABV (Alcohol by Weight) = " + beer.abv
        beer_description.text = beer.description
        beer_description.movementMethod = ScrollingMovementMethod()
        Picasso.get().load(beer.image_url).into(beer_image)
    }

}