package com.example.coronatracker.ui.globalstats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coronatracker.R
import com.example.coronatracker.data.model.CountryStats
import kotlinx.android.synthetic.main.item_country_list.view.*

class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    private  var countryList : List<CountryStats> = mutableListOf()

    class CountryListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: CountryStats, position: Int) {
            itemView.tvCountry.text = country.country + country.newCases + "new cases"
            //itemView.txtTitle.text = articles.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val rootView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_country_list, parent, false)
        return CountryListViewHolder(rootView)
    }

    override fun getItemCount(): Int = this.countryList.size

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        holder.bind(countryList[position], position)
    }

    fun setCountryList(countries : List<CountryStats>){
        this.countryList = countries
        notifyDataSetChanged()
    }
}
