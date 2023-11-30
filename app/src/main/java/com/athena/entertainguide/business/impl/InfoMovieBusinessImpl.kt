package com.athena.entertainguide.business.impl

import com.athena.entertainguide.business.InfoMovieBusiness
import com.athena.entertainguide.entity.MovieInfoEntities
import com.athena.entertainguide.repository.MovieRepository
import com.athena.entertainguide.repository.entity.response.MovieInfoResponse
import com.athena.entertainguide.response.ResultWrapper
import com.athena.entertainguide.response.toResult
import com.athena.entertainguide.utils.numbers.toBigDecimalWithPrecision
import java.util.Locale

internal class InfoMovieBusinessImpl(
    private val repository: MovieRepository
) : InfoMovieBusiness {

    override suspend fun getMovieDetail(movieId: Int): ResultWrapper<MovieInfoEntities> {
        return repository.getMovieDetail(getLanguage(), movieId).toResult().map(::convertResponseToListMovieEntities)
    }

    private fun convertResponseToListMovieEntities(movieInfoResponse: MovieInfoResponse): MovieInfoEntities {
        return MovieInfoEntities(
            id = movieInfoResponse.id,
            title = movieInfoResponse.title,
            backdropPath = movieInfoResponse.backdropPath.orEmpty(),
            releaseDate = movieInfoResponse.releaseDate,
            runtime = movieInfoResponse.runtime,
            voteAverage = toBigDecimalWithPrecision(movieInfoResponse.voteAverage),
            overview = movieInfoResponse.overview
        )
    }

    private fun getLanguage(): String {
        val locale = Locale.getDefault()
        val language = locale.language
        val country = locale.country

        return "$language-$country"
    }
}