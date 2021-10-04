package com.backbase.assignment.moviebox.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.backbase.assignment.moviebox.ui.ViewModelFactory
import com.backbase.assignment.moviebox.ui.detail.DetailViewModel
import com.backbase.assignment.moviebox.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    single {
        viewModels(get(), get(), get())
    }

    viewModel { HomeViewModel(get(), get(), ioDispatcher = get(named("ioDispatcher"))) }

    viewModel { DetailViewModel(get(), get(), ioDispatcher = get(named("ioDispatcher"))) }
}

private fun viewModels(
    viewModelStoreOwner: ViewModelStoreOwner,
    viewModelFactory: ViewModelFactory,
    viewModel: ViewModel
) = ViewModelProvider(viewModelStoreOwner, viewModelFactory)
    .get(viewModel::class.java)