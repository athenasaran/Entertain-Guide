package com.athena.entertainguide.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    fun execute(context: CoroutineContext, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(context = context, block = block)
}