package com.ideas2It.icare.server.model.dto

import com.ideas2It.icare.server.model.entity.Role

class UserDTO {
    var id : Long = 0
    var firstName : String? = ""
    var lastName : String? = ""
    var username: String = ""
    var role: String = ""
}
