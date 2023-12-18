package com.ideas2It.icare.server.service

import com.ideas2It.icare.server.model.dto.LoginRequestDTO


interface UserService {

//    fun authenticateUser(request : LoginRequestDTO) : Map<String, Any>

    fun getGoogleProfile(accessToken: String) : String
}