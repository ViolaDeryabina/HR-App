package com.example.hr_app.domain

import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.ProfileDto
import com.example.hr_network_library.data.dto.RegisterRequest
import com.example.hr_network_library.domain.repository.AuthenticationRepository
import javax.inject.Inject

// Назначение: Use Case для выполнения запроса на регистрацию в профиль
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026

class RegisterUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {
    suspend operator fun invoke(registerRequest: RegisterRequest): NetworkResult<ProfileDto>{
        return authenticationRepository.register(registerRequest)
    }
}