package com.example.myapplication.ui.home

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
import com.example.myapplication.model.Results
import java.lang.Exception

class TrendingAdapter(var context: Context, var data:ArrayList<Results>): RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    var onItemClick: ((Results) -> Unit)? = null
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView
        val textViewLanguage: TextView
        val textViewReleaseDate: TextView
        val ratingBar : RatingBar
        val imageViewBanner : ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textViewTitle = view.findViewById(R.id.textViewTitle)
            textViewLanguage = view.findViewById(R.id.textViewLanguage)
            textViewReleaseDate = view.findViewById(R.id.textViewReleaseDate)
            ratingBar = view.findViewById(R.id.ratingBar)
            imageViewBanner = view.findViewById(R.id.imageViewBanner)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_trending_items, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewTitle.text = data[position].title
        viewHolder.textViewLanguage.text = data[position].original_language
        viewHolder.textViewReleaseDate.text = data[position].release_date
        viewHolder.ratingBar.rating = data[position].vote_average

        try {
            val url:String=data[position].poster_path
            Glide.with(context).asBitmap().load("https://image.tmdb.org/t/p/w185"+url).into(
                BitmapImageViewTarget(viewHolder.imageViewBanner)
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
        viewHolder.itemView.setOnClickListener {
            onItemClick?.invoke(data[position])
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}