package com.athena.entertainguide.business

import com.athena.entertainguide.entity.PopularEntities
import com.athena.entertainguide.entity.TopRatedEntities
import com.athena.entertainguide.response.ResultWrapper

internal interface SeriesBusiness {

    suspend fun getPopularSeries(page: Int): ResultWrapper<PopularEntities>

    suspend fun getTopRatedSeries(page: Int): ResultWrapper<TopRatedEntities>
}