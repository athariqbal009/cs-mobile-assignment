package com.backbase.assignment.moviebox.ui.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.backbase.assignment.moviebox.R
import com.backbase.assignment.moviebox.data.remote.ApiParams
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList
import com.backbase.assignment.moviebox.data.remote.response.home.Result
import com.backbase.assignment.moviebox.utils.inflate
import com.bumptech.glide.Glide

class RVAdapterHorizontal: RecyclerView.Adapter<RVAdapterHorizontal.MovieHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(parent.inflate(R.layout.recycler_view_movie_list_horizontal,false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        differ.currentList[position]?.let { holder.bindMovie(it) }
    }

    override fun getItemCount(): Int {
      return differ.currentList.size
    }

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       fun bindMovie(results: Result) {
           val poster = itemView.findViewById(R.id.imageViewMoviePoster) as ImageView
           //Glide.with(itemView).load(ApiParams.URL_POSTER + results.posterPath).into(poster)
           poster.load(ApiParams.URL_POSTER + results.posterPath)
        }
    }
}