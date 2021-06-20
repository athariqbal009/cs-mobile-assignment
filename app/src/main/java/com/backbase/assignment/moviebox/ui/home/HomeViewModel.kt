package com.backbase.assignment.moviebox.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.backbase.assignment.moviebox.data.remote.ApiAdapter
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import com.backbase.assignment.moviebox.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG: String = "HomeViewModel"

class HomeViewModel(private val context: Context) : ViewModel() {
    private val movieListHorizontal: MutableLiveData<MovieList> by lazy {
        MutableLiveData<MovieList>().also {
            loadMovieListHorizontally()
        }
    }

    private val movieListVertical: MutableLiveData<MovieList> by lazy {
        MutableLiveData<MovieList>().also {
            loadMovieListVertically()
        }
    }

    fun getMovieListHorizontal(): LiveData<MovieList> {
        return movieListHorizontal
    }

    fun getMovieListVertical(): LiveData<MovieList> {
        return movieListVertical
    }

    private fun loadMovieListHorizontally() {
        if (Utils.isConnected(context)) {
            val callback = object : Callback<MovieList> {
                override fun onResponse(
                    call: Call<MovieList>,
                    response: Response<MovieList>
                ) {
                    response.isSuccessful.let {
                        Log.v(TAG, "isSuccessfull{${it}}")
                        movieListHorizontal.value = response.body()
                    }
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    Log.e(TAG, "Problem calling MovieDB API {${t.message}}")
                }
            }
            ApiAdapter().movieListHorizontally(callback)
        } else {
            Log.d(TAG, "No Connectivity")
        }
    }

    private fun loadMovieListVertically() {
        if (Utils.isConnected(context)) {
            val callback = object : Callback<MovieList> {
                override fun onResponse(
                    call: Call<MovieList>,
                    response: Response<MovieList>
                ) {
                    response.isSuccessful.let {
                        Log.v(TAG, "isSuccessfull{${it}}")
                        movieListVertical.value = response.body()
                    }
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    Log.e(TAG, "Problem calling MovieDB API {${t.message}}")
                }
            }
            ApiAdapter().movieListVertically(callback)
        } else {
            Log.d(TAG, "No Connectivity")
        }
    }

    companion object {
        fun getViewModel(context: Context, homeViewModel: HomeViewModel):HomeViewModel {
            return ViewModelProvider(context as ViewModelStoreOwner).get(homeViewModel::class.java)
        }
    }
}