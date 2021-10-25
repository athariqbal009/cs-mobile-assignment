package com.backbase.assignment.moviebox.di

import com.backbase.assignment.moviebox.ui.home.RVAdapterHorizontal
import com.backbase.assignment.moviebox.ui.home.RVAdapterVertical
import org.koin.dsl.module

val adapterModule = module {
    single {
        verticalAdapter()
    }

    single {
        horizontalAdapter()
    }
}

private fun verticalAdapter() = RVAdapterVertical()

private fun horizontalAdapter() = RVAdapterHorizontal()