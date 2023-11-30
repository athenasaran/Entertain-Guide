package com.athena.entertainguide.entity

internal data class NowPlayingEntities(
    val resultList: List<ResultItemNowPlaying>,
    val totalPages: Int
)

internal data class ResultItemNowPlaying(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Number,
    val releaseDate: String
)