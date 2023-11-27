package com.athena.entertainguide.remoteconfig.toggle

import com.athena.entertainguide.remoteconfig.ConfigFeatureToggle

enum class SeriesFeatureToggleKeys(val key: String, val defaultValue: Boolean) {
    SERIES_ENABLED("ft_series_enabled", true);

    companion object {
        fun getDefaultValueMap(): Map<String, Boolean> {
            val map: MutableMap<String, Boolean> = mutableMapOf()
            for (value in MovieFeatureToggleKeys.values()) {
                map[value.key] = value.defaultValue
            }
            return map
        }
    }
}

fun ConfigFeatureToggle.getBoolean(toggleKey: SeriesFeatureToggleKeys) = this.getBoolean(toggleKey.key)