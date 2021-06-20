package com.example.myapplication.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.myapplication.R
import com.example.myapplication.model.Results
import java.lang.Exception


class BannerAdapter(var context: Context, val imageUrls:ArrayList<Results>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val viewItem: View = inflater.inflate(R.layout.layout_banner, container, false)
        val imageView = viewItem.findViewById<View>(R.id.imageView) as ImageView
        val title = viewItem.findViewById<View>(R.id.textViewTitle)as TextView
        val date = viewItem.findViewById<View>(R.id.textViewReleaseDate)as TextView
        title.setText(imageUrls[position].title)
        date.setText(imageUrls[position].release_date)
        var url:String=imageUrls[position].backdrop_path.replace("http","https")
        try {
            Glide.with(context).asBitmap().load("https://image.tmdb.org/t/p/w500"+url).into(
                BitmapImageViewTarget(imageView))
        }catch (e:Exception){
            e.printStackTrace()
        }

        (container as ViewPager).addView(viewItem)
        return viewItem
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return imageUrls.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}