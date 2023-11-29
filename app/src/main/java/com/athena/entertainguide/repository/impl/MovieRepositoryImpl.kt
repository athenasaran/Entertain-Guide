package com.athena.entertainguide.repository.impl

import com.athena.entertainguide.api.MovieApi
import com.athena.entertainguide.repository.MovieRepository
import com.athena.entertainguide.repository.entity.response.MovieInfoResponse
import com.athena.entertainguide.repository.entity.response.PopularResponse
import com.athena.entertainguide.repository.entity.response.TopRatedResponse
import com.athena.entertainguide.response.NetworkResponse

internal class MovieRepositoryImpl(
    private var api: MovieApi
) : MovieRepository {
    override suspend fun getPopularMovie(page: String, language: String): NetworkResponse<PopularResponse> =
        api.getPopularMovie(languageId = language, page = page)

    override suspend fun getTopRatedMovie(page: String, language: String): NetworkResponse<TopRatedResponse> =
        api.getTopRatedMovie(languageId = language, page = page)

    override suspend fun getMovieDetail(language: String, movieId: Int): NetworkResponse<MovieInfoResponse> =
        api.getMovieDetail(languageId = language, movieId = movieId)

}