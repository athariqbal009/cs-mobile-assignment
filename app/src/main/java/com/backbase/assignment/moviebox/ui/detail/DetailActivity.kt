package com.backbase.assignment.moviebox.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.backbase.assignment.moviebox.data.remote.ApiParams
import com.backbase.assignment.moviebox.data.remote.response.detail.Genre
import com.backbase.assignment.moviebox.databinding.ActivityDetailBinding
import com.backbase.assignment.moviebox.ui.ViewModelFactory
import com.backbase.assignment.moviebox.utils.Utils
import com.google.android.material.chip.Chip

private const val TAG: String = "DetailActivity"

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupVM()
    }

    private fun setupVM() {
        viewModelFactory = ViewModelFactory(this)
        DetailViewModel.getViewModel(this, viewModel)
        setupUI()
    }

    private fun setupUI() {
        viewModel.movieId = intent.extras?.getInt(ApiParams.MOVIE_ID)!!
        viewModel.getMovieDetails().observe(this, {
            binding.textView3.visibility = View.VISIBLE
            Log.v(TAG, it.title.toString())
            binding.imageViewPoster.load(ApiParams.URL_POSTER + it.posterPath)
            binding.textViewTitle.text = it.title
            binding.textViewDateTime.text = Utils.convertDate(it.releaseDate.toString())
            binding.textViewOverView.text = it.overview
            setupGenres(it.genres)
        })
    }

    private fun setupGenres(genres: List<Genre>?) {
        if (genres != null) {
            for ((index, genre) in genres.withIndex()) {
                val item: Genre = genre
                val chip = Chip(this)
                chip.text = item.name
                binding.chipViewGenres.addView(chip)
            }
        }
    }

    fun onBackPressed(view:View) {
        super.onBackPressed()
        this.finish()
    }
}