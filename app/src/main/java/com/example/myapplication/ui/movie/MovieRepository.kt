package com.example.myapplication.ui.movie

import com.example.myapplication.model.MovieDetails
import com.example.myapplication.model.MoviesNow
import com.example.myapplication.model.MoviesTrending
import com.example.myapplication.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository (val api: Api) {
    fun getNowMoviesDetails(id:Int): Observable<MovieDetails> {
        return Observable.create{ emitter->
            api.getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.body() != null) {
                        emitter.onNext(it.body()!!)
                    }
                }, {
                    it.printStackTrace()
                })
        }
    }

    fun getNowMoviesRecommended(id: Int): Observable<MoviesTrending> {
        return Observable.create{ emitter->
            api.getMovieRecommendations(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.body() != null) {
                        emitter.onNext(it.body()!!)
                    }
                }, {
                    it.printStackTrace()
                })
        }
    }
}