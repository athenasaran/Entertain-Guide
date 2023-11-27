package com.athena.entertainguide.remoteconfig.toggle

import com.athena.entertainguide.remoteconfig.ConfigFeatureToggle

enum class GamesFeatureToggleKeys(val key: String, val defaultValue: Boolean) {
    GAMES_ENABLED("ft_games_enabled", true);

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

fun ConfigFeatureToggle.getBoolean(toggleKey: GamesFeatureToggleKeys) = this.getBoolean(toggleKey.key)