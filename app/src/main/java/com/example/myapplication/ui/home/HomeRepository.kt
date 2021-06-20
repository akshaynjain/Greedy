package com.example.myapplication.ui.home

import com.example.myapplication.model.MoviesNow
import com.example.myapplication.model.MoviesTrending
import com.example.myapplication.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeRepository(val api:Api) {

    fun getNowMovies(): Observable<MoviesNow> {
        return Observable.create{ emitter->
            api.getNowPlaying()
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

    fun getNowMoviestrending(page:Int): Observable<MoviesTrending> {
        return Observable.create{ emitter->
            api.getTrending(page)
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