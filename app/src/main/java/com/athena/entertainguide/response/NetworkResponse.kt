package com.athena.entertainguide.response

sealed class NetworkResponse<out T> {
    data class Success<out T>(val value: T) : NetworkResponse<T>()
    data class Error(@Transient val exception: Exception? = null) : NetworkResponse<Nothing>()
}

fun <T> NetworkResponse<T>.toResult(): ResultWrapper<T> =
    when (this) {
        is NetworkResponse.Success -> {
            ResultWrapper.Success(this.value)
        }

        is NetworkResponse.Error -> {
            ResultWrapper.Error(failure = this.exception ?: Exception())
        }
    }