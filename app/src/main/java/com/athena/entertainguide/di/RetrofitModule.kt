package com.athena.entertainguide.di

import com.athena.entertainguide.BuildConfig
import com.athena.entertainguide.BuildConfig.API_KEY
import com.athena.entertainguide.api.MovieApi
import com.athena.entertainguide.response.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private fun createMovieApi(
    factory: Moshi
): MovieApi {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(factory)) //Convert JSON API responses into Kotlin objects
        .addCallAdapterFactory(NetworkResponseAdapterFactory()) // Groups API responses into a NetworkResponse that provides additional information about the response (custom converter)
        .client(interceptor()) // Adds additional headers to HTTP requests
        .build()
        .create(MovieApi::class.java)// Creates an instance of MovieApi from the constructed Retrofit object
}

private fun interceptor(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    } // Shows logs with request and response information

    val requestInterceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request) // Create an Interceptor object (monitors, rewrites and tries call again)
        // which intercepts requests and adds the api_key parameter to the request URL
    }

    return OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)// adds the Interceptor with the tmdb api key
        .addInterceptor(loggingInterceptor) // adds the logging Interceptor
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

val retrofitModule = module {

    single { createMovieApi(factory = get()) } // Single instance of a dependency throughout the application lifecycle
    factory {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // Moshi Adapter that automatically converts Kotlin classes to and from JSON data
            .build()
    }// Create a new instance of a dependency every time an injection is requested. Useful for dependencies that need to be created dynamically, such as objects that depend on external data.
}