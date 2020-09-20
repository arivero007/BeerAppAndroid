package com.arivero007.beerappandroid.ui.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.adapters.BeersAdapter
import com.arivero007.beerappandroid.ui.models.BeersListViewModel
import com.arivero007.beerappandroid.utils.webservice.Beer
import kotlinx.android.synthetic.main.fragment_beers_list.*


class BeersListFragment : Fragment() {

    private val beersModel: BeersListViewModel by activityViewModels()
    private lateinit var beers: List<Beer>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BeersAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        //Listen for model
        beersModel!!.getBeers().observe(this, Observer<List<Beer>>{
            if (it != null){
                beers = it
                setUpRecyclerView()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beers_list, container, false)
    }

    fun updateList(text: String){
        viewAdapter.filter.filter(text)
    }

    //RecyclerView
    fun setUpRecyclerView(){
        recyclerView = beers_view
        viewManager = LinearLayoutManager(requireContext())
        viewAdapter = BeersAdapter(beersModel, requireActivity(), beers)
        recyclerView.layoutManager = viewManager
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20);
        recyclerView.adapter = viewAdapter
    }

}