package com.athena.entertainguide.remoteconfig.toggle

import com.athena.entertainguide.remoteconfig.ConfigFeatureToggle

enum class MovieFeatureToggleKeys(val key: String, val defaultValue: Boolean) {
    MOVIE_ENABLED("ft_movie_enabled", true);

    companion object {
        fun getDefaultValueMap(): Map<String, Boolean> {
            val map: MutableMap<String, Boolean> = mutableMapOf()
            for (value in values()) {
                map[value.key] = value.defaultValue
            }
            return map
        }
    }
}

fun ConfigFeatureToggle.getBoolean(toggleKey: MovieFeatureToggleKeys) = this.getBoolean(toggleKey.key)