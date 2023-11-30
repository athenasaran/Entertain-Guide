package com.athena.entertainguide.business

import com.athena.entertainguide.entity.NowPlayingEntities
import com.athena.entertainguide.response.ResultWrapper

internal interface EntertainmentBusiness {

    suspend fun getNowPlaying(page: Int): ResultWrapper<NowPlayingEntities>

}