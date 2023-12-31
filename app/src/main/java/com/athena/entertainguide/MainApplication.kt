package com.athena.entertainguide

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.athena.entertainguide.remoteconfig.ConfigFeatureToggle
import com.athena.entertainguide.remoteconfig.toggle.GamesFeatureToggleKeys
import com.athena.entertainguide.remoteconfig.toggle.MovieFeatureToggleKeys
import com.athena.entertainguide.remoteconfig.toggle.SeriesFeatureToggleKeys
import com.athena.entertainguide.starter.StarterModules

class MainApplication : Application() {

    private val starterModules by lazy { StarterModules(this) }
    private val configFeatureToggle by lazy { ConfigFeatureToggle() }

    override fun onCreate() {
        super.onCreate()
        setupNightMode()
        starterModules.start()
        configFeatureToggle.featureToggle()
    }

    private fun ConfigFeatureToggle.featureToggle() {
        configureRemote(GamesFeatureToggleKeys.getDefaultValueMap())
        configureRemote(MovieFeatureToggleKeys.getDefaultValueMap())
        configureRemote(SeriesFeatureToggleKeys.getDefaultValueMap())
    }

    private fun setupNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}