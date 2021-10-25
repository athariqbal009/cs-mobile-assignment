package com.backbase.assignment.moviebox.domain

import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import com.backbase.assignment.moviebox.utils.Resource

interface MovieRepository {
    suspend fun getMovieListHorizontally(): Resource<MovieList>
    suspend fun getMovieListVertically(page: Int): Resource<MovieList>
    suspend fun getMovieDetails(id: Int): Resource<MovieDetails>
}