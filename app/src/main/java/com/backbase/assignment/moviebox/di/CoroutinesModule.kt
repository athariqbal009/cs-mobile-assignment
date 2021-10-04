package com.backbase.assignment.moviebox.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutinesModule = module {
    factory(named("defaultDispatcher")) { default() }
    factory(named("ioDispatcher")) { io() }
    factory(named("mainDispatcher")) { main() }
}

private fun default(): CoroutineDispatcher = Dispatchers.Default

private fun io(): CoroutineDispatcher = Dispatchers.IO

private fun main(): CoroutineDispatcher = Dispatchers.Main