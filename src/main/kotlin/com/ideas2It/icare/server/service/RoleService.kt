package com.ideas2It.icare.server.service

import com.ideas2It.icare.server.model.entity.Role


interface RoleService {
    fun findRoleByName(roleName: String?) : Role
}