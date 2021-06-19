package com.backbase.assignment.moviedb.data.remote

class ApiParams {
    companion object {
        private const val API_KEY = "55957fcf3ba81b137f8fc01ac5a31fb5"
        const val MOVIE_ID = "movie_id"
        const val PAGE_NO = "page"
        const val URL_MOVIE_LIST_HORIZONTAL = "movie/now_playing?language=en-US&page=undefined&api_key=$API_KEY"
        const val URL_MOVIE_LIST_VERTICAL = "movie/popular?api_key=$API_KEY&language=en-US"
        const val URL_MOVIE_DETAILS = "movie/{$MOVIE_ID}?api_key=$API_KEY&language=en-US"
        const val URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }
}