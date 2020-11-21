package com.arivero007.beerappandroid.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.ui.models.BeersListViewModel
import com.arivero007.beerappandroid.utils.webservice.Beer
import com.arivero007.beerappandroid.utils.webservice.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeersListActivity : AppCompatActivity() {

    private val TAG = "BeerListActivity: "

    private val beersModel: BeersListViewModel by viewModels()
    private lateinit var beers: List<Beer>

    private lateinit var fragmentManager: FragmentManager
    private lateinit var beerListFragment: BeersListFragment
    private lateinit var beerFragment: BeerFragment

    //LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager
        beerListFragment = BeersListFragment()
        beerFragment = BeerFragment()

        fragmentManager.beginTransaction().add(R.id.fragment_container, beerListFragment).commit()

        downloadListOfBeers()
    }

    fun showBeerFragment(){
        fragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.fragment_container, beerFragment)
            addToBackStack(null)
        }.commit()
    }

    //REST
    fun downloadListOfBeers(){

        val retrofit = RetrofitBuilder
        retrofit.apiService.getListOfBeers().enqueue(
            object: Callback<List<Beer>> {
                override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                    val res = response.body()
                    if (res != null){
                        beers = res
                        beersModel?.setBeers(beers)
                        fragmentManager.fragments[0].onResume()
                    }
                }

                override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}