package com.athena.entertainguide.ui.initial.state

import com.athena.entertainguide.entity.TopRatedEntities

internal sealed interface TopRatedMovieState {
    data class Success(val entities: TopRatedEntities) : TopRatedMovieState
    data class Error(val exception: Exception) : TopRatedMovieState
    object Loading : TopRatedMovieState
}

internal sealed interface TopRatedSeriesState {
    data class Success(val entities: TopRatedEntities) : TopRatedSeriesState
    data class Error(val exception: Exception) : TopRatedSeriesState
    object Loading : TopRatedSeriesState
}