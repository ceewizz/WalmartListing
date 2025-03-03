package com.mywalmartapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mywalmartapp.model.Country
import com.mywalmartapp.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.mywalmartapp.repository.Result
import com.mywalmartapp.repository.usecase.GetCountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _countries = MutableStateFlow<Result>(Result.Success(emptyList<Country>()))
    val countries: StateFlow<Result> = _countries

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun getCountriesFromRepository() {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = getCountriesUseCase.execute()) {
                is Result.Success -> {
                    _countries.value = result
                }
                is Result.Error -> {
                    _errorMessage.value = result.message
                }
            }
            _isLoading.value = false
        }
    }
}