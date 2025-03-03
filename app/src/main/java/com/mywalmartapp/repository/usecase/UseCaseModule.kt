package com.mywalmartapp.repository.usecase

import com.mywalmartapp.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countryRepository: CountryRepository): GetCountriesUseCase {
        return GetCountriesUseCase(countryRepository)
    }
}