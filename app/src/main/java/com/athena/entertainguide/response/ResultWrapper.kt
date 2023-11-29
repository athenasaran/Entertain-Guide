package com.athena.entertainguide.response

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val failure: Exception) : ResultWrapper<Nothing>()

    fun <M> map(mapper: (T) -> M): ResultWrapper<M> = when (this) {
        is Success -> Success(mapper(value))
        is Error -> Error(failure = failure)
    }

    fun isSuccessful() = this is Success
    fun isError() = this is Error

    fun toSuccess() = this as Success
    fun toError() = this as Error
}

inline fun <T> ResultWrapper<T>.onSuccess(action: (value: T) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.Success<T>) {
        action(value)
    }
    return this
}

inline fun <T> ResultWrapper<T>.onFailure(action: (exception: Exception) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.Error) {
        action(failure)
    }
    return this
}