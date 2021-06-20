package com.example.myapplication.ui.movie

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.MovieDetails
import com.example.myapplication.model.MoviesTrending

class MovieViewModel( val movieRepository: MovieRepository):ViewModel() {

    private val _moviesNowList : MutableLiveData<MovieDetails> = MutableLiveData()
    val moviesNowList : LiveData<MovieDetails> = _moviesNowList

    private val _moviesRecommended : MutableLiveData<MoviesTrending> = MutableLiveData()
    val moviesRecommended : LiveData<MoviesTrending> = _moviesRecommended

    @SuppressLint("CheckResult")
    fun getMovieDetails(id:Int){
        movieRepository.getNowMoviesDetails(id).subscribe {
            _moviesNowList.postValue(it)
        }
    }

    @SuppressLint("CheckResult")
    fun getMovieRecommended(id: Int){
        movieRepository.getNowMoviesRecommended(id).subscribe {
            _moviesRecommended.postValue(it)
        }
    }

}