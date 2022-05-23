package com.example.appmvil.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmvil.R
import com.example.appmvil.model.Country

class CountryAdapter(private var countrys: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_country, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        vh.bind(countrys[position])
    }

    override fun getItemCount(): Int {
        return countrys.size
    }

    fun update(data: List<Country>) {
        countrys = data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.findViewById(R.id.textViewName)
        private val textViewNameShort: TextView = view.findViewById(R.id.textViewLink)
        private val imageView: ImageView = view.findViewById(R.id.imageView)
        fun bind(country: Country) {
            textViewName.text = country.country_name.capitalize()
            textViewNameShort.text = country.country_short_name
            Glide.with(imageView.context).load(R.drawable.ic_launcher_background).into(imageView)
        }
    }
}