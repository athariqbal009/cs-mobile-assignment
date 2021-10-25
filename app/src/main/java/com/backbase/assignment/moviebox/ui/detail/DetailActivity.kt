package com.backbase.assignment.moviebox.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.backbase.assignment.moviebox.data.remote.ApiParams
import com.backbase.assignment.moviebox.data.remote.response.detail.Genre
import com.backbase.assignment.moviebox.databinding.ActivityDetailBinding
import com.backbase.assignment.moviebox.utils.Resource
import com.backbase.assignment.moviebox.utils.Utils
import com.backbase.assignment.moviebox.utils.showToast
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG: String = "DetailActivity"

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        lifecycle.addObserver(viewModel)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        viewModel.movieId = intent.extras?.getInt(ApiParams.MOVIE_ID)!!
        viewModel.movieDetails.observe(this, { response ->
            when (response) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response.data?.let {
                        binding.textView3.visibility = View.VISIBLE
                        Log.v(TAG, it.title.toString())
                        //Glide.with(this).load(ApiParams.URL_POSTER + it.posterPath).into(binding.imageViewPoster)
                        binding.imageViewPoster.load(ApiParams.URL_POSTER + it.posterPath)
                        binding.textViewTitle.text = it.title
                        binding.textViewDateTime.text = Utils.convertDate(it.releaseDate.toString())
                        binding.textViewOverView.text = it.overview
                        setupGenres(it.genres)
                    }
                }
                is Resource.Error -> {
                    showToast(this, response.message.toString())
                }
            }
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

    fun onBackPressed(view: View) {
        super.onBackPressed()
        this.finish()
    }
}