package com.mywalmartapp.repository

import com.mywalmartapp.model.Country
import com.mywalmartapp.repository.network.APIService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val apiService: APIService) :
    CountryRepository {

    override suspend fun getCountries(): Result {
        return try {
            val response = apiService.getCountries()
            if (response.isSuccessful) {
                Result.Success(response.body() ?: emptyList<Country>())
            } else {
                Result.Error("HTTP Error: ${response.code()}")
            }
        } catch (e: IOException) {
            Result.Error("Network Error: ${e.message}")
        } catch (e: HttpException) {
            Result.Error("HTTP Error: ${e.message}")
        } catch (e: Exception) {
            Result.Error("Error: ${e.message}")
        }
    }
}
