package com.athena.entertainguide.ui.initial.state

import com.athena.entertainguide.entity.MovieInfoEntities

internal sealed interface InfoMovieState {
    data class Success(val entities: MovieInfoEntities) : InfoMovieState
    data class Error(val exception: Exception) : InfoMovieState
    object Loading : InfoMovieState
}