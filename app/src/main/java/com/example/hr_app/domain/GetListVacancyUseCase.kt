package com.example.hr_app.domain

import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.VacancyDto
import com.example.hr_network_library.data.dto.VacancyResponse
import com.example.hr_network_library.domain.repository.VacancyRepository
import javax.inject.Inject

// Назначение: Use Case для выполнения запроса на списка вакансий
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026
class GetListVacancyUseCase @Inject constructor(private val vacancyRepository: VacancyRepository) {
    suspend operator fun invoke(token: String): NetworkResult<VacancyResponse>{
        return vacancyRepository.getListVacancy(token)
    }
}