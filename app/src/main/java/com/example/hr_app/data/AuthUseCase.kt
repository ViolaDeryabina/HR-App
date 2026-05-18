package com.example.hr_app.data

import com.example.hr_network_library.data.dataSources.service.AuthenticationService
import com.example.hr_network_library.data.dto.AuthRequest
import com.example.hr_network_library.data.dto.AuthResponse
import javax.inject.Inject


class AuthUseCase @Inject constructor (private val api: AuthenticationService) {
    suspend operator fun invoke(authRequest: AuthRequest): AuthResponse {
        return api.authorization(authRequest)
    }
}