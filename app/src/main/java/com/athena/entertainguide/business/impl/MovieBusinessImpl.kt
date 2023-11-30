package com.athena.entertainguide.business.impl

import com.athena.entertainguide.business.MovieBusiness
import com.athena.entertainguide.entity.PopularEntities
import com.athena.entertainguide.entity.ResultItemPopular
import com.athena.entertainguide.entity.ResultItemTopRated
import com.athena.entertainguide.entity.TopRatedEntities
import com.athena.entertainguide.repository.MovieRepository
import com.athena.entertainguide.repository.entity.response.PopularResponse
import com.athena.entertainguide.repository.entity.response.ResultItemPopularResponse
import com.athena.entertainguide.repository.entity.response.ResultItemTopRatedResponse
import com.athena.entertainguide.repository.entity.response.TopRatedResponse
import com.athena.entertainguide.response.ResultWrapper
import com.athena.entertainguide.response.toResult
import com.athena.entertainguide.utils.numbers.toBigDecimalWithPrecision
import java.util.Locale

internal class MovieBusinessImpl(
    private val repository: MovieRepository
) : MovieBusiness {

    override suspend fun getPopularMovie(page: Int): ResultWrapper<PopularEntities> {
        return repository.getPopularMovie(page, getLanguage()).toResult().map(::convertResponseToPopularEntities)
    }

    override suspend fun getTopRatedMovie(page: Int): ResultWrapper<TopRatedEntities> {
        return repository.getTopRatedMovie(page, getLanguage()).toResult().map(::convertResponseToTopRatedEntities)
    }


    private fun getResultTopRated(resultList: List<ResultItemTopRatedResponse>): List<ResultItemTopRated> {
        val items = mutableListOf<ResultItemTopRated?>()

        resultList.forEach { listItemTopRated ->
            val resultItemPopular = ResultItemTopRated(
                id = listItemTopRated.id,
                posterPath = listItemTopRated.posterPath,
                title = listItemTopRated.title,
                voteAverage = listItemTopRated.voteAverage
            )
            items.add(resultItemPopular)
        }

        return items.filterNotNull()
    }

    private fun getResultPopular(resultList: List<ResultItemPopularResponse>): List<ResultItemPopular> {
        val items = mutableListOf<ResultItemPopular?>()

        resultList.forEach { listItemPopular ->
            val resultItemPopular = ResultItemPopular(
                id = listItemPopular.id,
                posterPath = listItemPopular.posterPath,
                title = listItemPopular.title,
                voteAverage = toBigDecimalWithPrecision(listItemPopular.voteAverage)
            )
            items.add(resultItemPopular)
        }

        return items.filterNotNull()
    }

    private fun convertResponseToPopularEntities(popularResponse: PopularResponse): PopularEntities = PopularEntities(
        resultList = getResultPopular(popularResponse.resultList),
        totalPages = popularResponse.totalPages
    )

    private fun convertResponseToTopRatedEntities(topRatedResponse: TopRatedResponse): TopRatedEntities =
        TopRatedEntities(
            resultList = getResultTopRated(topRatedResponse.resultList),
            totalPages = topRatedResponse.totalPages
        )

    private fun getLanguage(): String {
        val locale = Locale.getDefault()
        val language = locale.language
        val country = locale.country

        return "$language-$country"
    }
}