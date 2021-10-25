package com.backbase.assignment.moviebox.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.backbase.assignment.moviebox.data.remote.response.home.MovieList

class MovieListVerticallyDataSource: PagingSource<Int, MovieList>() {
    override fun getRefreshKey(state: PagingState<Int, MovieList>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieList> {
        TODO("Not yet implemented")
    }
}