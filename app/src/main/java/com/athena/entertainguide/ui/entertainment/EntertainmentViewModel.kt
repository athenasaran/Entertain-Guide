package com.athena.entertainguide.ui.entertainment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.athena.entertainguide.business.EntertainmentBusiness
import com.athena.entertainguide.response.onFailure
import com.athena.entertainguide.response.onSuccess
import com.athena.entertainguide.ui.base.BaseViewModel
import com.athena.entertainguide.ui.initial.state.EntertainmentState
import kotlin.coroutines.CoroutineContext

internal class EntertainmentViewModel(
    private val coroutineContext: CoroutineContext,
    private val business: EntertainmentBusiness
) : BaseViewModel() {

    private val _stateEntertainment = MutableLiveData<EntertainmentState>()
    val stateEntertainment: LiveData<EntertainmentState> = _stateEntertainment

    fun fetchNowPlaying() {
        _stateEntertainment.value = EntertainmentState.Loading
        execute(coroutineContext) {
            business.getNowPlaying(PAGE_INITIAL).onSuccess {
                _stateEntertainment.value = EntertainmentState.Success(it)
            }.onFailure {
                _stateEntertainment.value = EntertainmentState.Error(it)
            }
        }
    }

    private companion object {
        const val PAGE_INITIAL = 1
    }
}