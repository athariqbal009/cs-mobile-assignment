package com.backbase.assignment.moviebox.di

import com.backbase.assignment.moviebox.repository.local.LocalDataSource
import com.backbase.assignment.moviebox.repository.local.LocalDataSourceImpl
import org.koin.dsl.module

val localDataModule = module {
    single<LocalDataSource> {
        local()
    }
}

private fun local() = LocalDataSourceImpl()