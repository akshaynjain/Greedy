package com.example.myapplication.ui.castncrew

import com.example.myapplication.model.Credits
import com.example.myapplication.model.MovieDetails
import com.example.myapplication.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CastNCrewRepository(val api: Api) {

    fun getNowMoviesCredits(id:Int): Observable<Credits> {
        return Observable.create{ emitter->
            api.getMovieCasts(id)
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