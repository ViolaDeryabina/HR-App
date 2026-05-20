package com.example.hr_app.domain

import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.AuthRequest
import com.example.hr_network_library.data.dto.AuthResponse
import com.example.hr_network_library.domain.repository.AuthenticationRepository
import javax.inject.Inject

// Назначение: Use Case для выполнения запроса на вход в профиль
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026
class AuthUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {
    suspend operator fun invoke(authRequest: AuthRequest): NetworkResult<AuthResponse> {
        return authenticationRepository.authorization(authRequest)
    }
}