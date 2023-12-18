package com.ideas2It.icare.server.service.impl

import com.ideas2It.icare.server.model.entity.Role
import com.ideas2It.icare.server.repository.RoleRepository
import com.ideas2It.icare.server.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {

    override fun findRoleByName(roleName: String?) : Role {
        return roleRepository.findByName(roleName);
    }
}