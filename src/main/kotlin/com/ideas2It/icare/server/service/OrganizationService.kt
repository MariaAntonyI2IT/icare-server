package com.ideas2It.icare.server.service

import com.ideas2It.icare.server.model.dto.RegistrationRequestDTO
import com.ideas2It.icare.server.model.dto.ResponseDTO
import org.springframework.http.ResponseEntity


interface OrganizationService {
    fun verifyNgoId(request: String): ResponseEntity<Any>

    fun registerOraganization(organizationRequest: RegistrationRequestDTO): ResponseDTO

    fun getVerifiedOrganization(ngoId: String): HashMap<String, String>
}