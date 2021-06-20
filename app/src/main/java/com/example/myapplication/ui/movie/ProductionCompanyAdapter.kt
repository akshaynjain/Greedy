package com.example.myapplication.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.myapplication.R
import com.example.myapplication.model.ProductionCompanies
import java.util.*

class ProductionCompanyAdapter(var context: Context,var production_companies: ArrayList<ProductionCompanies>): RecyclerView.Adapter<ProductionCompanyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewBanner : ImageView
        init {
            imageViewBanner = view.findViewById(R.id.imageView3)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_prodcompanies_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val url:String=production_companies[position].logo_path
            Glide.with(context).asBitmap().load("https://image.tmdb.org/t/p/original"+url).into(
                BitmapImageViewTarget(holder.imageViewBanner)
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = production_companies.size
}