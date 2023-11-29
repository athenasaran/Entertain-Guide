package com.athena.entertainguide.repository.entity.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
internal data class PopularResponse(
    @Json(name = "results") val resultList: List<ResultItemPopularResponse>,
    @Json(name = "total_pages") val totalPages: Int
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
internal data class ResultItemPopularResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val voteAverage: Float
) : Parcelable