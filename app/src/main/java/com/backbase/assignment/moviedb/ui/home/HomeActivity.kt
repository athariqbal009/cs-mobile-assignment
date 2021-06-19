package com.backbase.assignment.moviedb.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.moviedb.data.remote.ApiParams
import com.backbase.assignment.moviedb.data.remote.response.home.Result
import com.backbase.assignment.moviedb.databinding.ActivityHomeBinding
import com.backbase.assignment.moviedb.ui.ViewModelFactory
import com.backbase.assignment.moviedb.ui.detail.DetailActivity

private const val TAG: String = "HomeActivity"

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private lateinit var recyclerViewHorizontal: RecyclerView
    private lateinit var recyclerViewVertical: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupVM()
    }

    private fun setupVM() {
        viewModelFactory = ViewModelFactory(this)
        HomeViewModel.getViewModel(this, viewModel)
        setupHRV()
    }

    private fun setupHRV() {
        recyclerViewHorizontal = binding.recyclerViewMovieListHorizontal
        viewModel.getMovieListHorizontal().observe(this, Observer {
            Log.v(TAG, it?.results?.get(0)?.title.toString())
            recyclerViewHorizontal.adapter = RVAdapterHorizontal(it)
            binding.textView1.visibility = View.VISIBLE
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

        recyclerViewVertical.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })

        viewModel.getMovieListVertical().observe(this, Observer {
            binding.textView2.visibility = View.VISIBLE
            Log.v(TAG, it?.results?.get(0)?.title.toString())
            recyclerViewVertical.adapter = it.results?.let { it1 ->
                RVAdapterVertical(it1) { result ->
                    openDetailActivity(result)
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