package com.backbase.assignment.moviebox.repository

import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import com.backbase.assignment.moviebox.domain.MovieRepository
import com.backbase.assignment.moviebox.repository.local.LocalDataSource
import com.backbase.assignment.moviebox.repository.remote.RemoteDataSource
import com.backbase.assignment.moviebox.utils.Resource
import retrofit2.Response

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): MovieRepository {
    override suspend fun getMovieListHorizontally(): Resource<MovieList> {
        return responseToResource(remoteDataSource.movieListHorizontally())
    }

    override suspend fun getMovieListVertically(): Resource<MovieList> {
        return responseToResource(remoteDataSource.movieListVertically())
    }

    override suspend fun getMovieDetails(id: Int): Resource<MovieDetails> {
        return responseToResource(remoteDataSource.movieDetails(id))
    }

    private fun <T> responseToResource(response: Response<T>):Resource<T>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}