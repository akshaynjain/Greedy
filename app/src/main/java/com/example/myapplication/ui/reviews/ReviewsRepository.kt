package com.example.myapplication.ui.reviews

import com.example.myapplication.model.MovieDetails
import com.example.myapplication.model.Reviews
import com.example.myapplication.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReviewsRepository(val api: Api) {
    fun getNowMovieReviews(id:Int): Observable<Reviews> {
        return Observable.create{ emitter->
            api.getMovieReviews(id)
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