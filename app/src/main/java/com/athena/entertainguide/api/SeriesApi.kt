package com.athena.entertainguide.api

import com.athena.entertainguide.repository.entity.response.PopularResponse
import com.athena.entertainguide.repository.entity.response.TopRatedResponse
import com.athena.entertainguide.response.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SeriesApi {

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("language") languageId: String,
        @Query("page") page: String
    ): NetworkResponse<PopularResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
        @Query("language") languageId: String,
        @Query("page") page: String
    ): NetworkResponse<TopRatedResponse>
}