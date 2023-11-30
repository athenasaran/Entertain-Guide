package com.athena.entertainguide.ui.initial.state

import com.athena.entertainguide.entity.NowPlayingEntities

internal sealed interface EntertainmentState {
    data class Success(val entities: NowPlayingEntities) : EntertainmentState
    data class Error(val exception: Exception) : EntertainmentState
    object Loading : EntertainmentState
}