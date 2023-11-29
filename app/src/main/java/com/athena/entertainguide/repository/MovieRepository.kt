package com.athena.entertainguide.repository

import com.athena.entertainguide.repository.entity.response.MovieInfoResponse
import com.athena.entertainguide.repository.entity.response.PopularResponse
import com.athena.entertainguide.repository.entity.response.TopRatedResponse
import com.athena.entertainguide.response.NetworkResponse

internal interface MovieRepository {

    suspend fun getPopularMovie(page: String, language: String): NetworkResponse<PopularResponse>

    suspend fun getTopRatedMovie(page: String, language: String): NetworkResponse<TopRatedResponse>

    suspend fun getMovieDetail(language: String, movieId: Int): NetworkResponse<MovieInfoResponse>

}