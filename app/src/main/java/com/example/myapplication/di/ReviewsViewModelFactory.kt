package com.example.myapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.reviews.ReviewViewModel
import com.example.myapplication.ui.reviews.ReviewsRepository

class ReviewsViewModelFactory(private val reviewsRepository: ReviewsRepository) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReviewViewModel(reviewsRepository)as T
    }
}