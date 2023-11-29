package com.athena.entertainguide.repository

import com.athena.entertainguide.repository.entity.response.PopularResponse
import com.athena.entertainguide.repository.entity.response.TopRatedResponse
import com.athena.entertainguide.response.NetworkResponse

internal interface SeriesRepository {

    suspend fun getPopularSeries(page: String, language: String): NetworkResponse<PopularResponse>

    suspend fun getTopRatedSeries(page: String, language: String): NetworkResponse<TopRatedResponse>
}