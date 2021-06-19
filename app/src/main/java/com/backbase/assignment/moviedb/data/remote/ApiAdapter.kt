package com.backbase.assignment.moviedb.data.remote

import com.backbase.assignment.moviedb.data.remote.response.MovieListModel
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiAdapter {
    companion object {
        private const val URL_BASE = "https://api.themoviedb.org/3/"
    }

    private val apiService : ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun movieListHorizontally(callback: Callback<MovieListModel>) {
        val call = apiService.getMovieListHorizontally()
        call.enqueue(callback)
    }
}