package com.backbase.assignment.moviebox.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.domain.MovieRepository
import com.backbase.assignment.moviebox.utils.Resource
import com.backbase.assignment.moviebox.utils.Utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG: String = "DetailViewModel"

class DetailViewModel(
    private val app: Application,
    private val movieRepository: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher
) : AndroidViewModel(app), DefaultLifecycleObserver {
    var movieId: Int = 0

    private val _movieDetails: MutableLiveData<Resource<MovieDetails>> by lazy {
        MutableLiveData<Resource<MovieDetails>>().also {
            loadMovieDetails()
        }
    }
    val movieDetails: LiveData<Resource<MovieDetails>> get() = _movieDetails

    private fun loadMovieDetails() = viewModelScope.launch {
        emitUiState(clazz = Resource.Loading())
        try {
            if (Utils.isConnected(app)) {
                val result = withContext(ioDispatcher) { movieRepository.getMovieDetails(movieId) }
                Log.v(TAG, "isSuccess:: $result")
                emitUiState(clazz = result)
            } else {
                emitUiState(clazz = Resource.Error("No Connectivity"))
            }
        } catch (e: Exception) {
            emitUiState(clazz = Resource.Error(e.message.toString()))
        }
    }

    private fun emitUiState(
        clazz: Resource<MovieDetails>
    ) {
        _movieDetails.value = clazz
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.d(TAG, "onCreate() from ViewModel")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d(TAG, "onStart() from ViewModel")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.d(TAG, "onPause() from ViewModel")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d(TAG, "onResume() from ViewModel")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d(TAG, "onStop() from ViewModel")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.d(TAG, "onDestroy() from ViewModel")
    }
}