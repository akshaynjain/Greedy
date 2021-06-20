package com.example.myapplication.ui.reviews

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.MoviesTrending
import com.example.myapplication.model.Reviews

class ReviewViewModel(val reviewsRepository: ReviewsRepository) : ViewModel() {
    private val _moviesReviews : MutableLiveData<Reviews> = MutableLiveData()
    val moviesReviews : LiveData<Reviews> = _moviesReviews

    @SuppressLint("CheckResult")
    fun getMovieReviews(id:Int){
        reviewsRepository.getNowMovieReviews(id).subscribe{
            _moviesReviews.postValue(it)
        }
    }
}