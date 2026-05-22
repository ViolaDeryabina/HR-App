package com.example.hr_app.domain

import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.Vacancy
import com.example.hr_network_library.data.dto.VacancyDto
import com.example.hr_network_library.domain.repository.VacancyRepository
import javax.inject.Inject

// Назначение: Use Case для выполнения запроса на создание вакансии
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026
class CreateVacancyUseCase @Inject constructor(private val api: VacancyRepository){
    suspend operator fun invoke(token: String, createVacancy: VacancyDto): NetworkResult<VacancyDto>{
        return api.createVacancy(token, createVacancy)
    }
}