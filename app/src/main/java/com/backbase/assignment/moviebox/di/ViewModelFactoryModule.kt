package com.backbase.assignment.moviebox.di

import android.app.Application
import com.backbase.assignment.moviebox.domain.MovieRepository
import com.backbase.assignment.moviebox.ui.ViewModelFactory
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelFactoryModule = module {
    single {
        vmFactory(
            app = get(),
            movieRepository = get(),
            ioDispatcher = get(named("ioDispatcher"))
        )
    }
}

private fun vmFactory(app: Application, movieRepository: MovieRepository, ioDispatcher: CoroutineDispatcher) = ViewModelFactory(
    app,
    movieRepository,
    ioDispatcher
)