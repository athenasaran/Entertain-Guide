package com.athena.entertainguide.ui.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.athena.entertainguide.business.MovieBusiness
import com.athena.entertainguide.response.onFailure
import com.athena.entertainguide.response.onSuccess
import com.athena.entertainguide.ui.base.BaseViewModel
import com.athena.entertainguide.ui.initial.state.PopularMovieState
import com.athena.entertainguide.ui.initial.state.TopRatedMovieState
import kotlin.coroutines.CoroutineContext

internal class InitialViewModel(
    private val coroutineContext: CoroutineContext,
    private val business: MovieBusiness
) : BaseViewModel() {

    private val _statePopularMovie = MutableLiveData<PopularMovieState>()
    val statePopularMovie: LiveData<PopularMovieState> = _statePopularMovie
    private val _stateTopRatedMovie = MutableLiveData<TopRatedMovieState>()
    val stateTopRatedMovie: LiveData<TopRatedMovieState> = _stateTopRatedMovie

    fun fetchPopularMovieItems() {
        _statePopularMovie.value = PopularMovieState.Loading
        execute(coroutineContext) {
            business.getPopularMovie(PAGE_INITIAL).onSuccess {
                _statePopularMovie.value = PopularMovieState.Success(it)
            }.onFailure {
                _statePopularMovie.value = PopularMovieState.Error(it)
            }
        }
    }

    fun fetchTopRatedMovie() {
        _stateTopRatedMovie.value = TopRatedMovieState.Loading
        execute(coroutineContext) {
            business.getTopRatedMovie(PAGE_INITIAL).onSuccess {
                _stateTopRatedMovie.value = TopRatedMovieState.Success(it)
            }.onFailure {
                _stateTopRatedMovie.value = TopRatedMovieState.Error(it)
            }
        }
    }

    private companion object {
        const val PAGE_INITIAL = 1
    }
}