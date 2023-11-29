package com.athena.entertainguide.business

import com.athena.entertainguide.entity.MovieInfoEntities
import com.athena.entertainguide.response.ResultWrapper

internal interface InfoMovieBusiness {

    suspend fun getMovieDetail(movieId: Int): ResultWrapper<MovieInfoEntities>

}