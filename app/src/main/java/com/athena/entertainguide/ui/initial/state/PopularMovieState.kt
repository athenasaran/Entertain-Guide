package com.athena.entertainguide.ui.initial.state

import com.athena.entertainguide.entity.PopularEntities

internal sealed interface PopularMovieState {
    data class Success(val entities: PopularEntities) : PopularMovieState
    data class Error(val exception: Exception) : PopularMovieState
    object Loading : PopularMovieState
}

internal sealed interface PopularSeriesState {
    data class Success(val entities: PopularEntities) : PopularSeriesState
    data class Error(val exception: Exception) : PopularSeriesState
    object Loading : PopularSeriesState
}