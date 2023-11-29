package com.athena.entertainguide.entity

internal data class PopularEntities(
    val resultList: List<ResultItemPopular>,
    val totalPages: Int
)

internal data class ResultItemPopular(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Float
)