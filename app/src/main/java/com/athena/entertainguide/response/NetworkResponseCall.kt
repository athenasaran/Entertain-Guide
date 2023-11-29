package com.athena.entertainguide.response

import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkResponseCall<T>(
    proxy: Call<T>
) : CallDelegate<T, NetworkResponse<T>>(proxy) {

    override fun executeImpl(): Response<NetworkResponse<T>> {

        val response = proxy.execute()
        val body = response.body()
        val code = response.code()

        val result = if (response.isSuccessful) {
            if (body != null) {
                NetworkResponse.Success(body)
            } else {
                responseError(code)
            }
        } else {
            responseError(code)
        }

        return Response.success(result)
    }

    override fun cloneImpl(): Call<NetworkResponse<T>> = NetworkResponseCall(proxy.clone())

    override fun timeout(): Timeout = proxy.timeout()

    override fun enqueueImpl(callback: Callback<NetworkResponse<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {

                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                val result = if (response.isSuccessful) {
                    if (body != null) {
                        NetworkResponse.Success(body)
                    } else {
                        responseError(code)
                    }
                } else {
                    responseError(code)
                }

                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(result)
                )
            }

            /*
        * It's always returning Generic error. Here we can handle, for example, errors in the request.
        */
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkResponse.Error(exception = Exception()))
                )
            }
        })
    }

    private fun responseError(
        code: Int
    ): NetworkResponse<T> {
        return NetworkResponse.Error(exception = Exception(code.toString()))
    }
}