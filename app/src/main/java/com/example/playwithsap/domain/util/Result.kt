package com.example.playwithsap.domain.util

import java.io.Serializable

sealed class MyResult<T>(
    val data: T? = null,
    val message: String? = null
) : Serializable {
    class Success<T>(data: T) : MyResult<T>(data)
    class Loading<T>(data: T? = null) : MyResult<T>(data)
    class Error<T>(message: String, data: T? = null) : MyResult<T>(data, message)
}