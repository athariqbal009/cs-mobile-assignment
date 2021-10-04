package com.backbase.assignment.moviebox.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.assignment.moviebox.domain.MovieRepository
import com.backbase.assignment.moviebox.ui.detail.DetailViewModel
import com.backbase.assignment.moviebox.ui.home.HomeViewModel
import kotlinx.coroutines.CoroutineDispatcher

class ViewModelFactory(
    private val app: Application,
    private val movieRepository: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(app, movieRepository, ioDispatcher) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(app, movieRepository, ioDispatcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}