package com.backbase.assignment.moviebox.di

import android.content.Context
import com.backbase.assignment.moviebox.BuildConfig
import com.backbase.assignment.moviebox.R
import com.backbase.assignment.moviebox.data.remote.ApiService
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val CACHE_FILE_SIZE: Long = 30 * 1000 * 1000 // 30 Mib

val networkModule = module {

    single(named("TMDB_URL")) { url(get()) }
    single(named("TMDB_KEY")) { key(get()) }

    single<Call.Factory> {
        val cacheFile = cacheFile(context = androidContext())
        val cache = cache(cacheFile = cacheFile)
        okhttp(cache = cache, apiKey = get(named("TMDB_KEY")))
    }

    single {
        retrofit(callFactory = get(), baseUrl = get(named("TMDB_URL")))
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}

private val interceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

private fun cacheFile(context: Context) = File(context.filesDir, "movie_cache").apply {
    if (!this.exists())
        mkdir()
}

private fun cache(cacheFile: File) = Cache(cacheFile, CACHE_FILE_SIZE)

private fun retrofit(callFactory: Call.Factory, baseUrl:String) = Retrofit.Builder()
    .callFactory(callFactory)
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun okhttp(cache: Cache, apiKey:String) = OkHttpClient.Builder()
    .addInterceptor{ chain->
        val request =chain.request().newBuilder()
        val originalHttpUrl =chain.request().url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language", "en-US")
            .build()
        request.url(url)
        return@addInterceptor chain.proceed(request.build())
    }
    .addInterceptor(interceptor)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .cache(cache)
    .build()

private fun key(
    context: Context
): String = context.getString(R.string.tmdb_key)

private fun url(
    context: Context
): String = context.getString(R.string.tmdb_url)