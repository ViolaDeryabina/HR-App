package com.example.hr_app.domain

import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.VacancyDto
import com.example.hr_network_library.domain.repository.VacancyRepository
import javax.inject.Inject

class GetListVacancyUseCase @Inject constructor(private val vacancyRepository: VacancyRepository) {
    suspend operator fun invoke(token: String): NetworkResult<VacancyDto>{
        return vacancyRepository.getListVacancy(token)
    }
}