package com.example.myapplication.ui.reviews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.myapplication.R
import com.example.myapplication.model.ProductionCompanies
import com.example.myapplication.model.Review
import com.google.android.material.imageview.ShapeableImageView
import java.util.*

class ReviewsAdapter(var context: Context, var review: ArrayList<Review>): RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewBanner : ShapeableImageView
        val textViewName : TextView
        val textViewContent : TextView
        val textViewDate : TextView
        val ratingBar : RatingBar
        init {
            imageViewBanner = view.findViewById(R.id.imageView4)
            textViewName = view.findViewById(R.id.textViewName)
            textViewContent = view.findViewById(R.id.textViewContent)
            textViewDate = view.findViewById(R.id.textViewDate)
            ratingBar = view.findViewById(R.id.ratingBar3)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_review_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewContent.text=review[position].content
        if (review[position].author_details.name==""){
            holder.textViewName.text=review[position].author_details.username
        }else{
            holder.textViewName.text=review[position].author_details.name
        }
        holder.textViewDate.text=review[position].created_at
        holder.ratingBar.rating= review[position].author_details.rating.toFloat()
        try {
            val url:String=review[position].author_details.avatar_path
            Glide.with(context).asBitmap().load("https://image.tmdb.org/t/p/original$url").into(
                BitmapImageViewTarget(holder.imageViewBanner))
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = review.size
}