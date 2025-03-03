package com.mywalmartapp.repository

interface CountryRepository {
    suspend fun getCountries(): Result
}
