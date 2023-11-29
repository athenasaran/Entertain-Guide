package com.athena.entertainguide.business.impl

import com.athena.entertainguide.business.InfoMovieBusiness
import com.athena.entertainguide.entity.MovieInfoEntities
import com.athena.entertainguide.repository.MovieRepository
import com.athena.entertainguide.repository.entity.response.MovieInfoResponse
import com.athena.entertainguide.response.ResultWrapper
import com.athena.entertainguide.response.toResult
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
            posterPath = movieInfoResponse.posterPath.orEmpty(),
            releaseDate = movieInfoResponse.releaseDate,
            runtime = movieInfoResponse.runtime,
            voteAverage = movieInfoResponse.voteAverage ?: 0.0,
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