package com.ideas2It.icare.server.service

import com.ideas2It.icare.server.model.dto.RegistrationRequestDTO
import com.ideas2It.icare.server.model.dto.ResponseDTO
import com.ideas2It.icare.server.model.entity.Organization


interface ContributorService {
    fun registerContributor(request: RegistrationRequestDTO) : ResponseDTO
}