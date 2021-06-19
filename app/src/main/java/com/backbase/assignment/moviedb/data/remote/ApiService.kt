package com.backbase.assignment.moviedb.data.remote

import com.backbase.assignment.moviedb.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviedb.data.remote.response.home.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface ApiService {

    @GET(ApiParams.URL_MOVIE_LIST_HORIZONTAL)
    fun getMovieListHorizontally(): Call<MovieList>

    @GET(ApiParams.URL_MOVIE_LIST_VERTICAL)
    fun getMovieListVertically(@Query(ApiParams.PAGE_NO) page_no: Int): Call<MovieList>

    @GET(ApiParams.URL_MOVIE_DETAILS)
    fun getMovieDetails(@Path(ApiParams.MOVIE_ID) movie_id: Int): Call<MovieDetails>

}