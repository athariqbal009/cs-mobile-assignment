package com.backbase.assignment.moviedb.data.remote

class ApiParams {
    companion object {
        private const val API_KEY = "55957fcf3ba81b137f8fc01ac5a31fb5"
        const val URL_MOVIE_LIST_HORIZONTAL = "movie/now_playing?language=en-US&page=undefined&api_key=$API_KEY"
        const val URL_MOVIE_LIST_VERTICAL = "movie/popular?api_key=$API_KEY&language=en-US&page={PAGE_NO}"
        const val URL_MOVIE_DETAILS = "{MOVIE_ID}?api_key=$API_KEY&language=en-US"
    }
}