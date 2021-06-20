package com.example.myapplication.network

import com.example.myapplication.model.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie/now_playing?page=1")
    fun getNowPlaying():Single<Response<MoviesNow>>

    @GET("trending/movie/week")
    fun getTrending(@Query("page") page: Int):Single<Response<MoviesTrending>>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")movie_id:Int):Single<Response<MovieDetails>>

    @GET("movie/{movie_id}/recommendations?page=1")
    fun getMovieRecommendations(@Path("movie_id")movie_id:Int):Single<Response<MoviesTrending>>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id")movie_id:Int):Single<Response<Reviews>>

    @GET("movie/{movie_id}/credits")
    fun getMovieCasts(@Path("movie_id")movie_id:Int):Single<Response<Credits>>
}