package com.arivero007.beerappandroid.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivero007.beerappandroid.utils.webservice.Beer

class BeersListViewModel : ViewModel() {

    private var _beers = MutableLiveData<List<Beer>>()
    private var _selectedBeer = MutableLiveData<Beer>()

    fun getBeers() : LiveData<List<Beer>> {
        return  _beers
    }

    fun setBeers(beers: List<Beer>) {
        _beers = MutableLiveData(beers)
    }

    fun getSelectedBeer() : LiveData<Beer> {
        return  _selectedBeer
    }

    fun setSelectedBeers(beer: Beer) {
        _selectedBeer = MutableLiveData(beer)
    }


}