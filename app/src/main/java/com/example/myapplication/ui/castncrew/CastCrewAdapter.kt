package com.example.myapplication.ui.castncrew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.myapplication.R
import com.example.myapplication.model.Cast
import com.google.android.material.imageview.ShapeableImageView
import java.util.*

class CastCrewAdapter (var context: Context, var credits: ArrayList<Cast>): RecyclerView.Adapter<CastCrewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewBanner : ShapeableImageView
        val textViewName : TextView
        val textViewCharacter : TextView
        init {
            imageViewBanner = view.findViewById(R.id.imageView5)
            textViewName = view.findViewById(R.id.textViewName)
            textViewCharacter = view.findViewById(R.id.textViewCharacter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_castcrew_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewCharacter.text=credits[position].character
        holder.textViewName.text=credits[position].name
        try {
            val url:String=credits[position].profile_path
            Glide.with(context).asBitmap().load("https://image.tmdb.org/t/p/original$url").into(
                BitmapImageViewTarget(holder.imageViewBanner)
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = credits.size
}