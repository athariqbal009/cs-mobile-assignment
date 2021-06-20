package com.backbase.assignment.moviebox.ui.detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.backbase.assignment.moviebox.data.remote.ApiAdapter
import com.backbase.assignment.moviebox.data.remote.response.detail.MovieDetails
import com.backbase.assignment.moviebox.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG: String = "DetailViewModel"

class DetailViewModel(private val context: Context) : ViewModel() {
    var movieId: Int = 0

    private val movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>().also {
            loadMovieDetails()
        }
    }

    fun getMovieDetails(): LiveData<MovieDetails> {
        return movieDetails
    }

    private fun loadMovieDetails() {
        if (Utils.isConnected(context)) {
            val callback = object : Callback<MovieDetails> {
                override fun onResponse(
                    call: Call<MovieDetails>,
                    response: Response<MovieDetails>
                ) {
                    response.isSuccessful.let {
                        Log.v(TAG, "isSuccessfull{${it}}")
                        movieDetails.value = response.body()
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    Log.e(TAG, "Problem calling MovieDB API {${t.message}}")
                }
            }
            ApiAdapter().movieDetails(callback, movieId)
        } else {
            Log.d(TAG, "No Connectivity")
        }
    }

    companion object {
        fun getViewModel(context: Context, detailViewModel: DetailViewModel): DetailViewModel {
            return ViewModelProvider(context as ViewModelStoreOwner).get(detailViewModel::class.java)
        }
    }
}