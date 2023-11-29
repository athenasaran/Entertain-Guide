package com.athena.entertainguide.starter

import android.app.Application
import com.athena.entertainguide.di.entertainGuideModule
import com.athena.entertainguide.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class StarterModules(private val application: Application) {

    fun start() {
        startKoin {
            androidContext(application)
            loadKoinModules(entertainGuideModule + retrofitModule)
        }
    }
}