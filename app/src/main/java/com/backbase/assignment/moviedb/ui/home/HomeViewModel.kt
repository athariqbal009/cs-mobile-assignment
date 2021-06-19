package com.backbase.assignment.moviedb.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.backbase.assignment.moviedb.data.remote.ApiAdapter
import com.backbase.assignment.moviedb.data.remote.response.MovieListModel
import com.backbase.assignment.moviedb.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG: String = "HomeViewModel"

class HomeViewModel(private val context: Context) : ViewModel() {
    private val movieList: MutableLiveData<MovieListModel> by lazy {
        MutableLiveData<MovieListModel>().also {
            loadMovieList()
        }
    }

    fun getMovieList(): LiveData<MovieListModel> {
        return movieList
    }

    private fun loadMovieList() {
        if (Utils.isConnected(context)) {
            val callback = object : Callback<MovieListModel> {
                override fun onResponse(
                    call: Call<MovieListModel>,
                    response: Response<MovieListModel>
                ) {
                    response.isSuccessful.let {
                        Log.v(TAG, "isSuccessfull{${it}}")
                        movieList.value = response.body()
                    }
                }

                override fun onFailure(call: Call<MovieListModel>, t: Throwable) {
                    Log.e(TAG, "Problem calling MovieDB API {${t.message}}")
                }
            }
            ApiAdapter().movieListHorizontally(callback)
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