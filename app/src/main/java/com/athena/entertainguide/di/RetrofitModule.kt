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
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .client(interceptor())
        .build()
        .create(MovieApi::class.java)
}

private fun interceptor(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

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

        return@Interceptor chain.proceed(request)
    }

    return OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

val retrofitModule = module {

    single { createMovieApi(factory = get()) }
    factory {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}