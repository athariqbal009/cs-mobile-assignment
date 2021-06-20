package com.backbase.assignment.moviebox.data.remote.response.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieList {
    @SerializedName("dates")
    @Expose
    var dates: Dates? = null

    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
}