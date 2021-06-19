package com.backbase.assignment.moviedb.data.remote

import com.backbase.assignment.moviedb.data.remote.response.MovieListModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(ApiParams.URL_MOVIE_LIST_HORIZONTAL)
    fun getMovieListHorizontally(): Call<MovieListModel>

}