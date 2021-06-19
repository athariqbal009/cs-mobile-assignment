package com.backbase.assignment.moviedb.ui.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.backbase.assignment.moviedb.R
import com.backbase.assignment.moviedb.data.remote.ApiParams
import com.backbase.assignment.moviedb.data.remote.response.home.MovieList
import com.backbase.assignment.moviedb.data.remote.response.home.Result
import com.backbase.assignment.moviedb.utils.inflate

class RVAdapterHorizontal(private val movieList: MovieList): RecyclerView.Adapter<RVAdapterHorizontal.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(parent.inflate(R.layout.recycler_view_movie_list_horizontal,false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        movieList.results?.get(position)?.let { holder.bindMovie(it) }
    }

    override fun getItemCount(): Int {
      return movieList.results?.size!!
    }

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            //itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

       fun bindMovie(results: Result) {
           val poster = itemView.findViewById(R.id.imageViewMoviePoster) as ImageView
           poster.load(ApiParams.URL_POSTER + results.posterPath)
        }
    }
}