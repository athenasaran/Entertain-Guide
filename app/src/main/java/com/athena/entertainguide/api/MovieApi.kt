package com.athena.entertainguide.api

import com.athena.entertainguide.repository.entity.response.MovieInfoResponse
import com.athena.entertainguide.repository.entity.response.NowPlayingResponse
import com.athena.entertainguide.repository.entity.response.PopularResponse
import com.athena.entertainguide.repository.entity.response.TopRatedResponse
import com.athena.entertainguide.response.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("language") languageId: String,
        @Query("page") page: Int
    ): NetworkResponse<PopularResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("language") languageId: String,
        @Query("page") page: Int
    ): NetworkResponse<TopRatedResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") languageId: String
    ): NetworkResponse<MovieInfoResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") languageId: String,
        @Query("page") page: Int
    ): NetworkResponse<NowPlayingResponse>
}