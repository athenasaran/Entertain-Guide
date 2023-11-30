package com.athena.entertainguide.repository.entity.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
internal data class MovieInfoResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "overview") val overview: String = String(),
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "title") val title: String = String(),
    @Json(name = "release_date") val releaseDate: String = String(),
    @Json(name = "runtime") val runtime: Int,
    @Json(name = "vote_average") val voteAverage: Double? = null
) : Parcelable