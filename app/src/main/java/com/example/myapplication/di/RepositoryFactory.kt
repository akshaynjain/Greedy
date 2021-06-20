package com.example.myapplication.di

import com.example.myapplication.network.Api
import com.example.myapplication.network.Client
import com.example.myapplication.ui.castncrew.CastNCrewRepository
import com.example.myapplication.ui.home.HomeRepository
import com.example.myapplication.ui.movie.MovieRepository
import com.example.myapplication.ui.reviews.ReviewsRepository


object RepositoryFactory {
        fun createHomeRepository() : HomeRepository {
            val tmdbApi = Client.instance.retrofit.create(Api::class.java)
            return HomeRepository(tmdbApi)
        }

        fun createMovieRepository() : MovieRepository {
            val tmdbApi = Client.instance.retrofit.create(Api::class.java)
            return MovieRepository(tmdbApi)
        }

        fun createReviewRepository():ReviewsRepository{
            val tmdbApi = Client.instance.retrofit.create(Api::class.java)
            return ReviewsRepository(tmdbApi)
        }

        fun createCastnCrewRepository():CastNCrewRepository{
            val tmdbApi = Client.instance.retrofit.create(Api::class.java)
            return CastNCrewRepository(tmdbApi)
        }

    }