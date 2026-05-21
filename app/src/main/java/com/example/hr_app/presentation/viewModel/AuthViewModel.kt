package com.example.hr_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hr_app.domain.AuthUseCase
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.ApiError
import com.example.hr_network_library.data.dto.AuthRequest
import com.example.hr_network_library.data.dto.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
// Назначение: 
// Автор: Дерябина В.Н.
// Дата создания 21-05-2026
@HiltViewModel
class AuthViewModel @Inject constructor (private val authUseCase: AuthUseCase): ViewModel() {

    private val _data = MutableStateFlow<NetworkResult<AuthResponse>?>(null)
    val data: StateFlow<NetworkResult<AuthResponse>?> = _data.asStateFlow()

    private val _error = MutableStateFlow<NetworkResult<ApiError>?>(null)
    val  error: StateFlow<NetworkResult<ApiError>?> = _error.asStateFlow()

    fun auth(authRequest: AuthRequest){
        _data.value = null
        viewModelScope.launch {
            when (val result = authUseCase.invoke(authRequest)) {
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