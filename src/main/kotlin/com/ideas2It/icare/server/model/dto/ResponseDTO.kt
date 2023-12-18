package com.ideas2It.icare.server.model.dto

import com.ideas2It.icare.server.model.entity.Role
import org.springframework.http.HttpStatus

data class ResponseDTO (
    var message: String? = "",
    var entity : Any? = "",
    var httpStatus: HttpStatus
)