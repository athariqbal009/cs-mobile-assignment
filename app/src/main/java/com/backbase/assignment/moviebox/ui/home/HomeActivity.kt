package com.backbase.assignment.moviebox.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.moviebox.data.remote.ApiParams
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import com.backbase.assignment.moviebox.data.remote.response.home.Result
import com.backbase.assignment.moviebox.databinding.ActivityHomeBinding
import com.backbase.assignment.moviebox.ui.detail.DetailActivity
import com.backbase.assignment.moviebox.utils.Resource
import com.backbase.assignment.moviebox.utils.showToast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG: String = "HomeActivity"

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    //private val factory: ViewModelFactory by inject()
    /*private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, factory)
            .get(HomeViewModel::class.java)
    }*/
    private val viewModel: HomeViewModel by viewModel()

    private val adapterHorizontal: RVAdapterHorizontal by inject()
    private val adapterVertical: RVAdapterVertical by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        lifecycle.addObserver(viewModel)
        setContentView(binding.root)
        setupHRV()
    }

    private fun setupHRV() {
        binding.recyclerViewMovieListHorizontal.apply {
            adapter = adapterHorizontal
        }
        viewModel.movieListHorizontal.observe(this, Observer { response ->
            when (response) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response.data?.let {
                        Log.v(TAG, response.data.results?.get(0)?.title.toString())
                        adapterHorizontal.differ.submitList(it.results)
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
        binding.recyclerViewMovieListVertical.apply {
            addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
            addOnScrollListener(this@HomeActivity.onScrollListener)
            adapter = adapterVertical
        }
        adapterVertical.setOnItemClickListener {
            openDetailActivity(it)
        }
        viewModel.loadMovieListVertically(page)
        viewModel.movieListVertical.observe(this, Observer { response ->
            when (response) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response.data?.let {
                        binding.textView2.visibility = View.VISIBLE
                        adapterVertical.differ.submitList(it.results?.toList())
                        pages = if(it.totalResults!! % 20 == 0) {
                            it.totalResults!! / 20
                        }else{
                            it.totalResults!! / 20 + 1
                        }
                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    showToast(this, response.message.toString())
                }
            }
        })
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager =  binding.recyclerViewMovieListVertical.layoutManager as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItem = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachToEnd = topPosition + visibleItem >= sizeOfCurrentList
            val shouldPaginate =  !isLastPage && hasReachToEnd && isScrolling
            if (shouldPaginate) {
                page++
                viewModel.loadMovieListVertically(page)
                isScrolling = false
            }
        }
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