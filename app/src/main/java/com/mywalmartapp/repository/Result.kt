package com.mywalmartapp.repository

sealed class Result {
        data class Success(val data: Any) : Result()
        data class Error(val message: String) : Result()
}
