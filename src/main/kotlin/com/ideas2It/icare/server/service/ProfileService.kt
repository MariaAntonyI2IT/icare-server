package com.ideas2It.icare.server.service

import com.ideas2It.icare.server.model.dto.ProfileResponse
import com.ideas2It.icare.server.model.dto.UserDTO

interface ProfileService {

    fun getProfileDetails(token: String) : ProfileResponse
}