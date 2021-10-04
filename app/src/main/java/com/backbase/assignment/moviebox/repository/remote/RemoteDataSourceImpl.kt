package com.backbase.assignment.moviebox.repository.remote

import com.backbase.assignment.moviebox.data.remote.ApiService
import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import retrofit2.Response

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun movieListHorizontally(): Response<MovieList> {
        return apiService.getMovieListHorizontally(page = "undefined")
    }

    override suspend fun movieListVertically(): Response<MovieList> {
        return apiService.getMovieListVertically(page_no = 1)
    }

    override suspend fun movieDetails(id: Int): Response<MovieDetails> {
        return apiService.getMovieDetails(movie_id = id)
    }
}