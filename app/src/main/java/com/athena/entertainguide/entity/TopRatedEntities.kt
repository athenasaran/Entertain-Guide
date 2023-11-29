package com.athena.entertainguide.entity

internal data class TopRatedEntities(
    val resultList: List<ResultItemTopRated>,
    val totalPages: Int
)

internal data class ResultItemTopRated(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Float
)