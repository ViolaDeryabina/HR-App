package com.example.hr_app.domain

import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.Vacancy
import com.example.hr_network_library.data.dto.VacancyDto
import com.example.hr_network_library.domain.repository.VacancyRepository
import javax.inject.Inject

class CreateVacancyUseCase @Inject constructor(private val api: VacancyRepository){
    suspend operator fun invoke(token: String, createVacancy: VacancyDto): NetworkResult<VacancyDto>{
        return api.createVacancy(token, createVacancy)
    }
}