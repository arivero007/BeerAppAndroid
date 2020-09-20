package com.arivero007.beerappandroid.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.add
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
            object: Callback<List<Beer>>{
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

    //Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val item = menu?.findItem(R.id.search_item)
        val search = item?.actionView as SearchView

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    beerListFragment.updateList(newText)
                }
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when(id){
            R.id.search_item -> {
                return true
            }
            R.id.refresh_item ->{
                downloadListOfBeers()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}