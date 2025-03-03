package com.mywalmartapp.repository.usecase

import com.mywalmartapp.repository.CountryRepository
import javax.inject.Inject
import com.mywalmartapp.repository.Result

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {

    suspend fun execute(): Result {
        return countryRepository.getCountries()
    }
}