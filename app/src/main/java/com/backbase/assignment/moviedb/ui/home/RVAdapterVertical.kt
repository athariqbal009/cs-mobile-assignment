package com.backbase.assignment.moviedb.ui.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.backbase.assignment.moviedb.R
import com.backbase.assignment.moviedb.data.remote.ApiParams
import com.backbase.assignment.moviedb.data.remote.response.MovieListModel
import com.backbase.assignment.moviedb.data.remote.response.Result
import com.backbase.assignment.moviedb.utils.Utils
import com.backbase.assignment.moviedb.utils.inflate

class RVAdapterVertical(private val movieListModel: MovieListModel): RecyclerView.Adapter<RVAdapterVertical.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(parent.inflate(R.layout.recycler_view_movie_list_vertical,false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        movieListModel.results?.get(position)?.let { holder.bindMovie(it) }
    }

    override fun getItemCount(): Int {
        return movieListModel.results?.size!!
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
            val title = itemView.findViewById(R.id.textViewTitle) as TextView
            val date = itemView.findViewById(R.id.textViewDate) as TextView
            val time = itemView.findViewById(R.id.textViewTime) as TextView

            poster.load(ApiParams.URL_POSTER + results.posterPath)
            title.text = results.title
            date.text = results.releaseDate?.let { Utils.convertDate(it) }
        }
    }
}