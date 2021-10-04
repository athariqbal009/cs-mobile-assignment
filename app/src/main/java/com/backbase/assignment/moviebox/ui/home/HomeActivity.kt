package com.backbase.assignment.moviebox.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.moviebox.data.remote.ApiParams
import com.backbase.assignment.moviebox.data.remote.response.home.Result
import com.backbase.assignment.moviebox.databinding.ActivityHomeBinding
import com.backbase.assignment.moviebox.ui.detail.DetailActivity
import com.backbase.assignment.moviebox.utils.Resource
import com.backbase.assignment.moviebox.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG: String = "HomeActivity"

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    //private val factory: ViewModelFactory by inject()
    /*private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, factory)
            .get(HomeViewModel::class.java)
    }*/
    private val viewModel: HomeViewModel by viewModel()

    private lateinit var recyclerViewHorizontal: RecyclerView
    private lateinit var recyclerViewVertical: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHRV()
    }

    private fun setupHRV() {
        recyclerViewHorizontal = binding.recyclerViewMovieListHorizontal
        viewModel.loadMovieListHorizontally()
        viewModel.movieListHorizontal.observe(this, Observer { response ->
            when (response) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response.data?.let {
                        Log.v(TAG, response.data.results?.get(0)?.title.toString())
                        recyclerViewHorizontal.adapter = RVAdapterHorizontal(it)
                        binding.textView1.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    showToast(this, response.message.toString())
                }
            }
        })
        setupVRV()
    }

    private fun setupVRV() {
        recyclerViewVertical = binding.recyclerViewMovieListVertical
        recyclerViewVertical.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        recyclerViewVertical.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        })

        viewModel.loadMovieListVertically()
        viewModel.movieListVertical.observe(this, Observer { response ->
            when (response) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response.data?.let {
                        binding.textView2.visibility = View.VISIBLE
                        Log.v(TAG, it.results?.get(0)?.title.toString())
                        recyclerViewVertical.adapter = it.results?.let { it1 ->
                            RVAdapterVertical(it1) { result ->
                                openDetailActivity(result)
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    showToast(this, response.message.toString())
                }
            }
        })
    }

    private fun openDetailActivity(result: Result) {
        startActivity(
            Intent(this, DetailActivity::class.java).putExtra(
                ApiParams.MOVIE_ID,
                result.id
            )
        )
    }
}