package com.arivero007.beerappandroid.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.beerappandroid.R
import com.arivero007.beerappandroid.ui.models.BeersListViewModel
import com.arivero007.beerappandroid.ui.views.BeerActivity
import com.arivero007.beerappandroid.utils.webservice.Beer
import kotlinx.android.synthetic.main.beer_recyclerview.view.*

class BeersAdapter(private val model: BeersListViewModel, private val context: Context, private val beers: List<Beer>): RecyclerView.Adapter<BeersAdapter.BeersHolder>(),
    Filterable {

    var beerHolder: BeersHolder? = null
    var filteredItems = beers

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.beer_recyclerview, parent, false)
        beerHolder = BeersHolder(view)

        return beerHolder!!
    }

    override fun onBindViewHolder(holder: BeersHolder, position: Int) {

        val beer = filteredItems[position]

        holder.name?.text = beer.name
        holder.beer.setOnClickListener {
            model.setSelectedBeers(beer)
        }
    }

    override fun getItemCount(): Int {
        return filteredItems.count()
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(text: CharSequence?): FilterResults {
                val searchText = text.toString()

                if(searchText.isEmpty()){
                    filteredItems = beers
                }else{
                    val temp = ArrayList<Beer>()

                    for (item in beers){
                        if (item.name.toLowerCase().contains(text.toString())){
                            temp.add(item)
                        }
                    }
                    filteredItems = temp
                }
                val results = FilterResults()
                results.values = filteredItems
                return results
            }

            override fun publishResults(text: CharSequence?, results: FilterResults?) {

                filteredItems = results?.values as List<Beer>
                notifyDataSetChanged()
            }

        }
    }

    class BeersHolder(view: View): RecyclerView.ViewHolder(view){

        var name: TextView
        var beer: ConstraintLayout

        init {
            name = view.beer_name
            beer = view.beer_item
        }
    }

}