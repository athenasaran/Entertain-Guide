package com.athena.entertainguide.remoteconfig

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class ConfigFeatureToggle {

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }
    private val defaults = mutableMapOf<String, Any>()


    fun configureRemote(values: Map<String, Any>) {
        remoteConfig.setConfigSettingsAsync(configSettings)
        defaults.putAll(values)
        remoteConfig.setDefaultsAsync(defaults)
    }

    fun getBoolean(key: String): Boolean = remoteConfig.getBoolean(key)
}