package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class MoviesNow(
    @SerializedName("dates") var dates: Dates,
    @SerializedName("results") var results: ArrayList<Results>,
    @SerializedName("page") var page: Float,
    @SerializedName("total_pages") var total_pages: Float,
    @SerializedName("total_results") var total_results: Float
) : Parcelable

@Parcelize
data class MoviesTrending(
    @SerializedName("results") var results: ArrayList<Results>,
    @SerializedName("page") var page: Int,
    @SerializedName("total_pages") var total_pages: Float,
    @SerializedName("total_results") var total_results: Float
) : Parcelable


@Parcelize
data class Dates(
    @SerializedName("maximum") var maximum: String = "",
    @SerializedName("minimum") var minimum: String = ""
):Parcelable

@Parcelize
data class Results(
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("backdrop_path") var backdrop_path: String = "",
    @SerializedName("genre_ids") var genre_ids: ArrayList<String>,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("original_language") var original_language: String = "",
    @SerializedName("original_title") var original_title: String = "",
    @SerializedName("overview") var overview: String? = "",
    @SerializedName("popularity") var popularity: Float = 0f,
    @SerializedName("poster_path") var poster_path: String = "",
    @SerializedName("release_date") var release_date: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("video") var video: Boolean = false,
    @SerializedName("vote_average") var vote_average: Float = 0f,
    @SerializedName("vote_count") var vote_count: Float = 0f
):Parcelable

@Parcelize
data class ProductionCompanies(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("logo_path") var logo_path: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("origin_country") var origin_country: String = ""
):Parcelable

@Parcelize
data class Spokenlanguages(
    @SerializedName("english_name") var english_name: String = "",
    @SerializedName("iso_639_1") var iso_639_1: String = "",
    @SerializedName("name") var name: String = ""
) : Parcelable

@Parcelize
data class Genres(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = ""
) : Parcelable

@Parcelize
data class BelongsToCollection(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("poster_path") var poster_path: String = "",
    @SerializedName("backdrop_path") var backdrop_path: String = ""
) : Parcelable

@Parcelize
data class Credits(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("cast") var cast: ArrayList<Cast>,
    @SerializedName("crew") var crew: ArrayList<Crew>
) : Parcelable

@Parcelize
data class Cast(
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("gender") var gender: Int = 0,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("known_for_department") var known_for_department: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("original_name") var original_name: String = "",
    @SerializedName("popularity") var popularity: Float = 0f,
    @SerializedName("profile_path") var profile_path: String = "",
    @SerializedName("cast_id") var cast_id: Float = 0f,
    @SerializedName("character") var character: String = "",
    @SerializedName("credit_id") var credit_id: String = "",
    @SerializedName("order") var order: Int = 0
) : Parcelable

@Parcelize
data class Crew(
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("gender") var gender: Int = 0,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("known_for_department") var known_for_department: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("original_name") var original_name: String = "",
    @SerializedName("popularity") var popularity: Float = 0f,
    @SerializedName("profile_path") var profile_path: String = "",
    @SerializedName("credit_id") var credit_id: String = "",
    @SerializedName("department") var department: String = "",
    @SerializedName("job") var job: String = ""
) : Parcelable


@Parcelize
data class MovieDetails(
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("backdrop_path") var backdrop_path: String = "",
    @SerializedName("belongs_to_collection") var belongs_to_collection: BelongsToCollection,
    @SerializedName("budget") var budget: Float = 0f,
    @SerializedName("genres") var genres: ArrayList<Genres> = ArrayList<Genres>(),
    @SerializedName("homepage") var homepage: String = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("imdb_id") var imdb_id: String = "",
    @SerializedName("original_language") var original_language: String = "",
    @SerializedName("original_title") var original_title: String = "",
    @SerializedName("overview") var overview: String = "",
    @SerializedName("popularity") var popularity: Float = 0f,
    @SerializedName("poster_path") var poster_path: String = "",
    @SerializedName("production_companies") var production_companies: ArrayList<ProductionCompanies> = ArrayList<ProductionCompanies>(),
    @SerializedName("production_countries") var production_countries: ArrayList<Genres> = ArrayList<Genres>(),
    @SerializedName("release_date") var release_date: String = "",
    @SerializedName("revenue") var revenue: Float = 0f,
    @SerializedName("runtime") var runtime: Float = 0f,
    @SerializedName("spoken_languages") var spoken_languages: ArrayList<Spokenlanguages> = ArrayList<Spokenlanguages>(),
    @SerializedName("status") var status: String = "",
    @SerializedName("tagline") var tagline: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("video") var video: Boolean = false,
    @SerializedName("vote_average") var vote_average: Float = 0f,
    @SerializedName("vote_count") var vote_count: Float = 0f
) : Parcelable

@Parcelize
data class Reviews (
    @SerializedName("id") var id: Int = 0,
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results:ArrayList<Review> = ArrayList<Review>(),
    @SerializedName("total_pages") var total_pages: Int = 0,
    @SerializedName("total_results") var total_results: Int = 0) : Parcelable

@Parcelize
data class Review(
    @SerializedName("id") var id: String = "",
    @SerializedName("author") var author: String = "",
    @SerializedName("author_details") var author_details: Author_details,
    @SerializedName("content") var content: String = "",
    @SerializedName("created_at") var created_at: String = "",
    @SerializedName("iso_639_1") var iso_639_1: String = "",
    @SerializedName("media_id") var media_id: Int = 0,
    @SerializedName("media_title") var media_title: String = "",
    @SerializedName("media_type") var media_type: String = "",
    @SerializedName("updated_at") var updated_at: String = "",
    @SerializedName("url") var url: String? = null
) : Parcelable

@Parcelize
data class Author_details(
    @SerializedName("name") var name: String = "",
    @SerializedName("username") var username: String = "",
    @SerializedName("avatar_path") var avatar_path: String = "",
    @SerializedName("rating") var rating: Int = 0
) : Parcelable