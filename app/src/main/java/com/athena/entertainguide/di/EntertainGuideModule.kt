package com.athena.entertainguide.di

import com.athena.entertainguide.business.InfoMovieBusiness
import com.athena.entertainguide.business.MovieBusiness
import com.athena.entertainguide.business.impl.InfoMovieBusinessImpl
import com.athena.entertainguide.business.impl.MovieBusinessImpl
import com.athena.entertainguide.component.tabbar.TabBarItemFactory
import com.athena.entertainguide.remoteconfig.ConfigFeatureToggle
import com.athena.entertainguide.repository.MovieRepository
import com.athena.entertainguide.repository.impl.MovieRepositoryImpl
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManager
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManagerImpl
import com.athena.entertainguide.ui.home.HomeViewModel
import com.athena.entertainguide.ui.infomovie.InfoMovieViewModel
import com.athena.entertainguide.ui.initial.InitialViewModel
import com.athena.entertainguide.utils.date.DateProvider
import com.athena.entertainguide.utils.date.DateProviderImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val entertainGuideModule = module {
    single { TabBarItemFactory(resources = androidContext().resources) }
    single<SharedPreferencesManager> { SharedPreferencesManagerImpl(context = androidContext()) }
    single<DateProvider> { DateProviderImpl() }
    single<MovieRepository> { MovieRepositoryImpl(api = get()) }
    single<MovieBusiness> { MovieBusinessImpl(repository = get()) }
    single<InfoMovieBusiness> { InfoMovieBusinessImpl(repository = get()) }
    single { ConfigFeatureToggle() }
    viewModel { HomeViewModel(sharedPreference = get(), dateProvider = get()) }
    viewModel { InitialViewModel(Dispatchers.Main, get()) }
    viewModel { InfoMovieViewModel(Dispatchers.Main, get()) }
}