package com.athena.entertainguide.response

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResponseAdapter(
    private val responseType: Type
) : CallAdapter<Type, Any> {

    override fun responseType(): Type = responseType
    override fun adapt(call: Call<Type>) = NetworkResponseCall(call)
}