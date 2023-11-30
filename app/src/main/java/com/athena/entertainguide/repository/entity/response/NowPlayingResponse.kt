package com.athena.entertainguide.repository.entity.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
internal data class NowPlayingResponse(
    @Json(name = "results") val resultList: List<ResultItemNowPlayingResponse>,
    @Json(name = "total_pages") val totalPages: Int
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
internal data class ResultItemNowPlayingResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "release_date") val releaseDate: String
) : Parcelable