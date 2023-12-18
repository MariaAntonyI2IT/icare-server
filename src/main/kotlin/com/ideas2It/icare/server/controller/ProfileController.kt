package com.ideas2It.icare.server.controller

import com.ideas2It.icare.server.model.dto.ProfileResponse
import com.ideas2It.icare.server.model.dto.UserDTO
import com.ideas2It.icare.server.service.ProfileService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@RestController()
@RequestMapping("/profile")
class ProfileController(private val profileService: ProfileService) {

    @PostMapping
    fun getProfile() : ResponseEntity<ProfileResponse> {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val response = profileService.getProfileDetails(request.getHeader(HttpHeaders.AUTHORIZATION))
        return ResponseEntity(response, HttpStatus.OK)
    }
}