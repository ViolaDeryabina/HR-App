package com.example.hr_app.domain

import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.Comment
import com.example.hr_network_library.domain.repository.HiringRepository
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor (private val hiringRepository: HiringRepository){
    suspend operator fun invoke(token: String): NetworkResult<Comment>{
        return hiringRepository.getListPositions(token)
    }
}