package com.backbase.assignment.moviebox.ui.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.backbase.assignment.moviebox.R
import com.backbase.assignment.moviebox.R.drawable
import com.backbase.assignment.moviebox.data.remote.ApiParams
import com.backbase.assignment.moviebox.data.remote.response.home.Result
import com.backbase.assignment.moviebox.utils.Utils
import com.backbase.assignment.moviebox.utils.inflate
import com.bumptech.glide.Glide

class RVAdapterVertical(
    private val movieList: List<Result>,
    private val onClick: (Result) -> Unit
) : RecyclerView.Adapter<RVAdapterVertical.MovieHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            parent.inflate(R.layout.recycler_view_movie_list_vertical, false),
            onClick
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = movieList[position]
        holder.setIsRecyclable(false)
        movieList[position].let {
            holder.bindMovie(item)
            holder.itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MovieHolder(itemView: View, val onClick: (Result) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bindMovie(results: Result) {
            val poster = itemView.findViewById(R.id.imageViewMoviePoster) as ImageView
            val title = itemView.findViewById(R.id.textViewTitle) as TextView
            val date = itemView.findViewById(R.id.textViewDate) as TextView
            val time = itemView.findViewById(R.id.textViewTime) as TextView

            //Glide.with(itemView).load(ApiParams.URL_POSTER + results.posterPath).into(poster)
            poster.load(ApiParams.URL_POSTER + results.posterPath)
            title.text = results.title
            date.text = results.releaseDate?.let { Utils.convertDate(it) }
            setupProgress(results)
        }

        private fun setupProgress(results: Result) {
            val progress = itemView.findViewById(R.id.progressBar) as ProgressBar
            val progressText = itemView.findViewById(R.id.textViewProgress) as TextView

            if (Utils.convertRating(results.voteAverage) >= 51) {
                progress.progressDrawable =
                    ResourcesCompat.getDrawable(itemView.resources, drawable.progress_green, null)
                progress.background = ResourcesCompat.getDrawable(
                    itemView.resources,
                    drawable.progress_circle_green,
                    null
                )
            } else {
                progress.progressDrawable =
                    ResourcesCompat.getDrawable(itemView.resources, drawable.progress_yellow, null)
                progress.background = ResourcesCompat.getDrawable(
                    itemView.resources,
                    drawable.progress_circle_yellow,
                    null
                )
            }

            progress.progress = Utils.convertRating(results.voteAverage)
            progressText.text = Utils.convertRating(results.voteAverage).toString().plus("%")
        }
    }
}