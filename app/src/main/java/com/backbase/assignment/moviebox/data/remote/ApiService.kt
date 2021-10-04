package com.backbase.assignment.moviebox.data.remote

import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getMovieListHorizontally(
        @Query("page") page: String
    ): Response<MovieList>

    @GET("movie/popular")
    suspend fun getMovieListVertically(
        @Query("page") page_no: Int
    ): Response<MovieList>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int
    ): Response<MovieDetails>

}