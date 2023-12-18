package com.ideas2It.icare.server.repository

import com.ideas2It.icare.server.model.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository:JpaRepository<Role, Long> {
     fun findByName(roleName: String?): Role

}