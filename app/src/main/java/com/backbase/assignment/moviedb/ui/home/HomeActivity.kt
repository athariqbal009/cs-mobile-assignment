package com.backbase.assignment.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.moviedb.databinding.ActivityHomeBinding
import com.backbase.assignment.moviedb.ui.ViewModelFactory

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
        setupHRV()
        setupVRV()
    }

    private fun setupVM() {
        viewModelFactory = ViewModelFactory(this)
        HomeViewModel.getViewModel(this, viewModel)
    }

    private fun setupHRV() {
        recyclerViewHorizontal = binding.recyclerViewMovieListHorizontal
        viewModel.getMovieList().observe(this, Observer {
            Log.v(TAG, it?.results?.get(0)?.title.toString())
            recyclerViewHorizontal.adapter = RVAdapterHorizontal(it)
        })
    }

    private fun setupVRV() {
        recyclerViewVertical = binding.recyclerViewMovieListVertical
        recyclerViewVertical.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        viewModel.getMovieList().observe(this, Observer {
            Log.v(TAG, it?.results?.get(0)?.title.toString())
            recyclerViewVertical.adapter = RVAdapterVertical(it)
        })
    }

    override fun onResume() {
        super.onResume()
    }

    /*fun openActivity(view: View) {
        startActivity(Intent(this, DetailActivity::class.java))
    }*/
}