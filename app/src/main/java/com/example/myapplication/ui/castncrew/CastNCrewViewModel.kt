package com.example.myapplication.ui.castncrew

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Credits
import com.example.myapplication.model.MoviesNow

class CastNCrewViewModel(val castNCrewRepository: CastNCrewRepository):ViewModel() {
    private val _moviesCast : MutableLiveData<Credits> = MutableLiveData()
    val moviesCast : LiveData<Credits> = _moviesCast

    @SuppressLint("CheckResult")
    fun getMovieCastnCrew(id:Int){
        castNCrewRepository.getNowMoviesCredits(id).subscribe {
            _moviesCast.postValue(it)
        }
    }
}