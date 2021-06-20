package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.MoviesNow
import com.example.myapplication.model.MoviesTrending

class HomeViewModel( val homeRepository: HomeRepository) :ViewModel(){

    private val _moviesNowList : MutableLiveData<MoviesNow> = MutableLiveData()
    val moviesNowList : LiveData<MoviesNow> = _moviesNowList

    private val _moviesTrendList : MutableLiveData<MoviesTrending> = MutableLiveData()
    val moviesTrendList : LiveData<MoviesTrending> = _moviesTrendList

    @SuppressLint("CheckResult")
    fun getMoviesNow(){
        homeRepository.getNowMovies().subscribe{
            _moviesNowList.postValue(it)
        }
    }

    @SuppressLint("CheckResult")
    fun getMoviesTrending(page:Int){
        homeRepository.getNowMoviestrending(page).subscribe{
            _moviesTrendList.postValue(it)
        }
    }
}