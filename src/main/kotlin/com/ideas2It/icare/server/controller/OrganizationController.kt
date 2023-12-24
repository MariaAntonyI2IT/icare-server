package com.ideas2It.icare.server.controller

import com.ideas2It.icare.server.annotations.TokenValidator
import com.ideas2It.icare.server.model.dto.RegistrationRequestDTO
import com.ideas2It.icare.server.model.dto.ResponseDTO
import com.ideas2It.icare.server.model.entity.Organization
import com.ideas2It.icare.server.service.OrganizationService
import com.ideas2It.icare.server.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/organization")
class OrganizationController(private val organizationService: OrganizationService) {

    @GetMapping("/verify-org-id")
    fun verifyNgoId(@RequestParam("ngoId") ngoId: String): Any {
//        val responseDTO: ResponseDTO = organizationService.verifyNgoId(ngoId);
        return organizationService.verifyNgoId(ngoId)
//        return ResponseEntity(responseDTO.entity, responseDTO.httpStatus);
    }

    @PostMapping("/register")
    fun registerOrganization(@RequestBody request: RegistrationRequestDTO): ResponseEntity<Any>? {
        val responseDTO: ResponseDTO = organizationService.registerOraganization(request);
        return ResponseEntity(responseDTO.entity, HttpStatus.OK);
    }

}