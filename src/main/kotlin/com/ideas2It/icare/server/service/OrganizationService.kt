package com.ideas2It.icare.server.service

import com.ideas2It.icare.server.model.dto.RegistrationRequestDTO
import com.ideas2It.icare.server.model.dto.ResponseDTO


interface OrganizationService {
    fun verifyNgoId(request: RegistrationRequestDTO): ResponseDTO

    fun registerOraganization(organizationRequest: RegistrationRequestDTO): ResponseDTO

    fun getVerifiedOrganization(ngoId: String): HashMap<String, String>
}