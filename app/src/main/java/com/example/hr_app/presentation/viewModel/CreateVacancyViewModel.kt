package com.example.hr_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hr_app.domain.CreateVacancyUseCase
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.ApiError
import com.example.hr_network_library.data.dto.Vacancy
import com.example.hr_network_library.data.dto.VacancyDto
import dagger.hilt.android.lifecycle.HiltViewModel
import io.appmetrica.analytics.impl.wh
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CreateVacancyViewModel @Inject constructor(private val api: CreateVacancyUseCase) : ViewModel() {

    private val _data = MutableStateFlow<NetworkResult<VacancyDto>?>(null)
    val data: StateFlow<NetworkResult<VacancyDto>?> = _data.asStateFlow()

    private val _error = MutableStateFlow<NetworkResult<ApiError>?>(null)
    val error: StateFlow<NetworkResult<ApiError>?> = _error.asStateFlow()

    fun createVacancy(token: String, vacancy: VacancyDto){
        viewModelScope.launch {
            _data.value=null
            when(val result = api.invoke(token, vacancy)){
                is NetworkResult.Success -> _data.value = NetworkResult.Success(result.data)
                is NetworkResult.Error -> _data.value = NetworkResult.Error(result.status, result.message)
                is NetworkResult.Exception -> _data.value = NetworkResult.Exception(result.message)

            }
        }

    }
}