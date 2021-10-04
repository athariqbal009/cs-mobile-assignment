package com.backbase.assignment.moviebox.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import com.backbase.assignment.moviebox.domain.MovieRepository
import com.backbase.assignment.moviebox.utils.Resource
import com.backbase.assignment.moviebox.utils.Utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private const val TAG: String = "HomeViewModel"

class HomeViewModel(
    private val app: Application,
    private val movieRepository: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val movieListHorizontal: MutableLiveData<Resource<MovieList>> by lazy {
        MutableLiveData<Resource<MovieList>>().also {
            loadMovieListHorizontally()
        }
    }

    val movieListVertical: MutableLiveData<Resource<MovieList>> by lazy {
        MutableLiveData<Resource<MovieList>>().also {
            loadMovieListVertically()
        }
    }

    fun loadMovieListHorizontally() = viewModelScope.launch(ioDispatcher) {
        movieListHorizontal.postValue(Resource.Loading())
        try {
            if (Utils.isConnected(app)) {
                val result = async { movieRepository.getMovieListHorizontally() }
                Log.v(TAG, "isSuccess:: $result")
                movieListHorizontal.postValue(result.await())
            } else {
                movieListHorizontal.postValue(Resource.Error("No Connectivity"))
            }
        } catch (e: Exception) {
            movieListHorizontal.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun loadMovieListVertically() = viewModelScope.launch(ioDispatcher) {
        movieListVertical.postValue(Resource.Loading())
        try {
            if (Utils.isConnected(app)) {
                val result = async { movieRepository.getMovieListVertically() }
                Log.v(TAG, "isSuccess:: $result")
                movieListVertical.postValue(result.await())
            } else {
                movieListVertical.postValue(Resource.Error("No Connectivity"))
            }
        } catch (e: Exception) {
            movieListVertical.postValue(Resource.Error(e.message.toString()))
        }
    }
}