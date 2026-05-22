package com.example.hr_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hr_app.domain.GetVacancyByIdUseCase
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.ApiError
import com.example.hr_network_library.data.dto.VacancyDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Назначение: Класс для управления состоянием экрана Детали вакансии
// Автор: Дерябина В.Н.
// Дата создания 21-05-2026
@HiltViewModel
class GetInfoById @Inject constructor(private val getVacancyByIdUseCase: GetVacancyByIdUseCase) : ViewModel() {
    private val _data = MutableStateFlow<NetworkResult<VacancyDto>?>(null)
    val data: StateFlow<NetworkResult<VacancyDto>?> = _data.asStateFlow()

    private val _error = MutableStateFlow<NetworkResult<ApiError>?>(null)
    val error: StateFlow<NetworkResult<ApiError>?> = _error.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)
    var loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun getInfoById(token: String, id: String) {
        viewModelScope.launch {
            _data.value = null
            _loading.value = true
            when (val result = getVacancyByIdUseCase.invoke(token, id)) {
                is NetworkResult.Success -> {
                    _loading.value = false
                    _data.value = NetworkResult.Success(result.data)
                }

                is NetworkResult.Error -> {
                    _loading.value = false
                    _error.value = NetworkResult.Error(result.status, result.message)
                }

                is NetworkResult.Exception -> {
                    _loading.value = false
                    _error.value = NetworkResult.Exception(result.message)
                }

            }
        }
    }
}