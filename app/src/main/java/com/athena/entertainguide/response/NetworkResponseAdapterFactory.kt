package com.athena.entertainguide.response

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory :
    CallAdapter.Factory() { // CallAdapter used to convert API responses to the desired type
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? { // The first * in is the type of object that will be returned and the second * in the second is the type of object that will be received
        return try {

            check(returnType is ParameterizedType) {
                "return type must be parametrized as Call<NetworkResponse<Foo>> or Call<NetworkResponse<out Foo>>"
            }

            val responseType = getParameterUpperBound(0, returnType)

            if (getRawType(responseType) != NetworkResponse::class.java) {
                return null
            }

            check(responseType is ParameterizedType) {
                "Response must be parameterized as NetworkResponse<Foo or NetworkResponse<out Foo>"
            }

            val successBodyType = getParameterUpperBound(0, responseType)

            return NetworkResponseAdapter(successBodyType)
        } catch (ex: ClassCastException) {
            null
        }
    }
}