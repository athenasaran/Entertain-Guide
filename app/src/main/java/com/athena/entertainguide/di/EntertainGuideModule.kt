package com.athena.entertainguide.di

import com.athena.entertainguide.component.tabbar.TabBarItemFactory
import com.athena.entertainguide.remoteconfig.ConfigFeatureToggle
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManager
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManagerImpl
import com.athena.entertainguide.ui.home.HomeViewModel
import com.athena.entertainguide.utils.date.DateProvider
import com.athena.entertainguide.utils.date.DateProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val entertainGuideModule = module {
    viewModel { HomeViewModel(sharedPreference = get(), dateProvider = get()) }
    single { TabBarItemFactory(resources = androidContext().resources) }
    factory { ConfigFeatureToggle() }
    single<SharedPreferencesManager> { SharedPreferencesManagerImpl(androidContext()) }
    single<DateProvider> { DateProviderImpl() }
}