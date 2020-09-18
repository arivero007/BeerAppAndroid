package com.arivero007.beerappandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.utils.webservice.Beer
import kotlinx.android.synthetic.main.beer_recyclerview.view.*

class BeersAdapter(private val beers: List<Beer>): RecyclerView.Adapter<BeersAdapter.BeersHolder>() {

    var beerHolder: BeersHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersAdapter.BeersHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.beer_recyclerview, parent, false)
        beerHolder = BeersHolder(view)

        return beerHolder!!
    }

    override fun onBindViewHolder(holder: BeersAdapter.BeersHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class BeersHolder(view: View): RecyclerView.ViewHolder(view){

        var name: TextView? = null

        init {
            name = view.beer_name
        }
    }


}