package com.backbase.assignment.moviebox.data.remote

import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
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

    fun movieListHorizontally(callback: Callback<MovieList>) {
        val call = apiService.getMovieListHorizontally()
        call.enqueue(callback)
    }

    fun movieListVertically(callback: Callback<MovieList>) {
        val call = apiService.getMovieListVertically(1)
        call.enqueue(callback)
    }

    fun movieDetails(callback: Callback<MovieDetails>, movieId: Int) {
        val call = apiService.getMovieDetails(movieId)
        call.enqueue(callback)
    }

}