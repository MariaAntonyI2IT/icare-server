package com.ideas2It.icare.server.model.dto

import com.ideas2It.icare.server.model.entity.Role

class UserDTO {
    var firstName : String? = ""
    var lastName : String? = ""
    var username: String = ""
    var role: Role = Role()
}