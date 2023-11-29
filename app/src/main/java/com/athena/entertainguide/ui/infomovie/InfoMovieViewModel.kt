package com.athena.entertainguide.ui.infomovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.athena.entertainguide.business.InfoMovieBusiness
import com.athena.entertainguide.response.onFailure
import com.athena.entertainguide.response.onSuccess
import com.athena.entertainguide.ui.base.BaseViewModel
import com.athena.entertainguide.ui.initial.state.InfoMovieState
import kotlin.coroutines.CoroutineContext

internal class InfoMovieViewModel(
    private val coroutineContext: CoroutineContext,
    private val business: InfoMovieBusiness
) : BaseViewModel() {

    private val _stateInfoMovie = MutableLiveData<InfoMovieState>()
    val stateInfoMovie: LiveData<InfoMovieState> = _stateInfoMovie

    fun fetchPopularMovieItems(movieId: Int) {
        _stateInfoMovie.value = InfoMovieState.Loading
        execute(coroutineContext) {
            business.getMovieDetail(movieId)
                .onSuccess {
                    _stateInfoMovie.value = InfoMovieState.Success(it)
                }.onFailure {
                    _stateInfoMovie.value = InfoMovieState.Error(it)
                }
        }
    }
}