package com.backbase.assignment.moviebox.ui.detail

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.domain.MovieRepository
import com.backbase.assignment.moviebox.utils.Resource
import com.backbase.assignment.moviebox.utils.Utils
import kotlinx.coroutines.*

private const val TAG: String = "DetailViewModel"

class DetailViewModel(
    private val app: Application,
    private val movieRepository: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    var movieId: Int = 0

    val movieDetails: MutableLiveData<Resource<MovieDetails>> by lazy {
        MutableLiveData<Resource<MovieDetails>>().also {
            loadMovieDetails()
        }
    }

    fun loadMovieDetails() = viewModelScope.launch {
        movieDetails.postValue(Resource.Loading())
        try {
            if (Utils.isConnected(app)) {
                val result = withContext(ioDispatcher) { movieRepository.getMovieDetails(movieId) }
                Log.v(TAG, "isSuccess:: $result")
                movieDetails.postValue(result)
            } else {
                movieDetails.postValue(Resource.Error("No Connectivity"))
            }
        } catch (e: Exception) {
            movieDetails.postValue(Resource.Error(e.message.toString()))
        }
    }
}