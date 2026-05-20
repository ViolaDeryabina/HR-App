package com.example.hr_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hr_app.domain.RegisterUseCase
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.ApiError
import com.example.hr_network_library.data.dto.ProfileDto
import com.example.hr_network_library.data.dto.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    private val _data = MutableStateFlow<NetworkResult<ProfileDto>?>(null)
    val data: StateFlow<NetworkResult<ProfileDto>?> = _data.asStateFlow()

    private val _error = MutableStateFlow<NetworkResult<ApiError>?>(null)
    val error: StateFlow<NetworkResult<ApiError>?> = _error.asStateFlow()

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            _data.value = null
            when (val result = registerUseCase.invoke(registerRequest)) {
                is NetworkResult.Success -> {
                    _data.value = NetworkResult.Success(result.data)
                }

                is NetworkResult.Error -> {
                    _error.value = NetworkResult.Error(result.status, result.message)
                }

                is NetworkResult.Exception -> {
                    _error.value = NetworkResult.Exception(result.message)
                }
            }
        }
    }
}