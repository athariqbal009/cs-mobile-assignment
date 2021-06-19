package com.backbase.assignment.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.backbase.assignment.moviedb.databinding.ActivityHomeBinding
import com.backbase.assignment.moviedb.ui.ViewModelFactory

private const val TAG: String = "HomeActivity"

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory(this)
        HomeViewModel.getViewModel(this, viewModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovieList().observe(this, Observer {
            Log.v(TAG, it?.results?.get(0)?.title.toString())
        })
    }

    /*fun openActivity(view: View) {
        startActivity(Intent(this, DetailActivity::class.java))
    }*/
}