package com.backbase.assignment.moviebox.di

import com.backbase.assignment.moviebox.domain.MovieRepository
import com.backbase.assignment.moviebox.repository.MovieRepositoryImpl
import com.backbase.assignment.moviebox.repository.local.LocalDataSource
import com.backbase.assignment.moviebox.repository.remote.RemoteDataSource
import org.koin.dsl.module

val movieModule = module {
    single<MovieRepository> {
        movie(get(), get())
    }
}

private fun movie(
    localDataSource: LocalDataSource,
    remoteDataSource: RemoteDataSource) = MovieRepositoryImpl(
    localDataSource,
    remoteDataSource
)