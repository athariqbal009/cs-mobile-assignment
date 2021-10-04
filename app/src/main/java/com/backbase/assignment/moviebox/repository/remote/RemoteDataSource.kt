package com.backbase.assignment.moviebox.repository.remote

import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import retrofit2.Response

interface RemoteDataSource {
    suspend fun movieListHorizontally(): Response<MovieList>
    suspend fun movieListVertically(): Response<MovieList>
    suspend fun movieDetails(id: Int): Response<MovieDetails>
}