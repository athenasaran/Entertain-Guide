package com.athena.entertainguide.business

import com.athena.entertainguide.entity.PopularEntities
import com.athena.entertainguide.entity.TopRatedEntities
import com.athena.entertainguide.response.ResultWrapper

internal interface MovieBusiness {

    suspend fun getPopularMovie(page: Int): ResultWrapper<PopularEntities>

    suspend fun getTopRatedMovie(page: Int): ResultWrapper<TopRatedEntities>

}