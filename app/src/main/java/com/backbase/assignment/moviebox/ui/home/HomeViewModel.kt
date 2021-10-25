package com.backbase.assignment.moviebox.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
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
) : AndroidViewModel(app), DefaultLifecycleObserver {
    private val _movieListHorizontal: MutableLiveData<Resource<MovieList>> by lazy {
        MutableLiveData<Resource<MovieList>>().also {
            loadMovieListHorizontally()
        }
    }

    private val _movieListVertical: MutableLiveData<Resource<MovieList>>  = MutableLiveData<Resource<MovieList>>()

    val movieListHorizontal: LiveData<Resource<MovieList>> get() = _movieListHorizontal
    val movieListVertical: LiveData<Resource<MovieList>> get() = _movieListVertical

    private fun loadMovieListHorizontally() = viewModelScope.launch {
        emitUiStateHorizontal(clazz =  Resource.Loading())
        try {
            if (Utils.isConnected(app)) {
                val result = async(ioDispatcher) { movieRepository.getMovieListHorizontally() }
                Log.v(TAG, "isSuccess:: $result")
                emitUiStateHorizontal(clazz = result.await())
            } else {
                emitUiStateHorizontal(clazz = Resource.Error("No Connectivity"))
            }
        } catch (e: Exception) {
            emitUiStateHorizontal(clazz = Resource.Error(e.message.toString()))
        }
    }

    fun loadMovieListVertically(page: Int) = viewModelScope.launch {
        emitUiStateVertical(clazz = Resource.Loading())
        try {
            if (Utils.isConnected(app)) {
                val result = async(ioDispatcher) { movieRepository.getMovieListVertically(page) }
                Log.v(TAG, "isSuccess:: $result")
                emitUiStateVertical(clazz = result.await())
            } else {
                emitUiStateVertical(clazz = Resource.Error("No Connectivity"))
            }
        } catch (e: Exception) {
            emitUiStateVertical(clazz = Resource.Error(e.message.toString()))
        }
    }

    private fun emitUiStateHorizontal(
         clazz: Resource<MovieList>
    ) {
        _movieListHorizontal.value = clazz
    }

    private fun emitUiStateVertical(
        clazz: Resource<MovieList>
    ) {
        _movieListVertical.value = clazz
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