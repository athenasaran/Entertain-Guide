package com.athena.entertainguide.repository.impl

import com.athena.entertainguide.api.SeriesApi
import com.athena.entertainguide.repository.SeriesRepository
import com.athena.entertainguide.repository.entity.response.PopularResponse
import com.athena.entertainguide.repository.entity.response.TopRatedResponse
import com.athena.entertainguide.response.NetworkResponse

internal class SeriesRepositoryImpl(private val api: SeriesApi) : SeriesRepository {

    override suspend fun getPopularSeries(page: String, language: String): NetworkResponse<PopularResponse> =
        api.getPopularSeries(languageId = language, page = page)

    override suspend fun getTopRatedSeries(page: String, language: String): NetworkResponse<TopRatedResponse> =
        api.getTopRatedSeries(languageId = language, page = page)
}