package com.athena.entertainguide.business.impl

import com.athena.entertainguide.business.EntertainmentBusiness
import com.athena.entertainguide.entity.NowPlayingEntities
import com.athena.entertainguide.entity.ResultItemNowPlaying
import com.athena.entertainguide.repository.MovieRepository
import com.athena.entertainguide.repository.entity.response.NowPlayingResponse
import com.athena.entertainguide.repository.entity.response.ResultItemNowPlayingResponse
import com.athena.entertainguide.response.ResultWrapper
import com.athena.entertainguide.response.toResult
import com.athena.entertainguide.utils.numbers.toBigDecimalWithPrecision
import java.util.Locale

internal class EntertainmentBusinessImpl(
    private val repository: MovieRepository
) : EntertainmentBusiness {

    override suspend fun getNowPlaying(page: Int): ResultWrapper<NowPlayingEntities> {
        return repository.getNowPlaying(language = getLanguage(), page = page).toResult()
            .map(::convertResponseToNowPlayingEntities)
    }

    private fun getResultNowPlaying(resultList: List<ResultItemNowPlayingResponse>): List<ResultItemNowPlaying> {
        val items = mutableListOf<ResultItemNowPlaying?>()

        resultList.forEach { listItemNowPlaying ->
            val resultItemNowPlaying = ResultItemNowPlaying(
                id = listItemNowPlaying.id,
                posterPath = listItemNowPlaying.posterPath,
                title = listItemNowPlaying.title,
                voteAverage = toBigDecimalWithPrecision(listItemNowPlaying.voteAverage),
                releaseDate = listItemNowPlaying.releaseDate
            )
            items.add(resultItemNowPlaying)
        }

        return items.filterNotNull()
    }

    private fun convertResponseToNowPlayingEntities(nowPlayingResponse: NowPlayingResponse): NowPlayingEntities =
        NowPlayingEntities(
            resultList = getResultNowPlaying(nowPlayingResponse.resultList),
            totalPages = nowPlayingResponse.totalPages
        )

    private fun getLanguage(): String {
        val locale = Locale.getDefault()
        val language = locale.language
        val country = locale.country

        return "$language-$country"
    }
}