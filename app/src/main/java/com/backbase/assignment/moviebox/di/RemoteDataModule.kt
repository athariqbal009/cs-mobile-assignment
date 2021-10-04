package com.backbase.assignment.moviebox.di

import com.backbase.assignment.moviebox.data.remote.ApiService
import com.backbase.assignment.moviebox.repository.remote.RemoteDataSource
import com.backbase.assignment.moviebox.repository.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module {
    single<RemoteDataSource> {
        remote(get())
    }
}

private fun remote(apiService: ApiService) = RemoteDataSourceImpl(apiService)