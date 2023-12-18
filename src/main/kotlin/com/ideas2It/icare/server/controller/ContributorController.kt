package com.ideas2It.icare.server.controller

import com.ideas2It.icare.server.model.dto.RegistrationRequestDTO
import com.ideas2It.icare.server.model.dto.ResponseDTO
import com.ideas2It.icare.server.service.ContributorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contributor")
class ContributorController(private val contributorService: ContributorService) {

    @PostMapping("/register")
    fun registerOrganization(@RequestBody request: RegistrationRequestDTO): ResponseEntity<Any>? {
        val responseDTO: ResponseDTO = contributorService.registerContributor(request);
        return ResponseEntity(responseDTO, HttpStatus.OK);
    }

}