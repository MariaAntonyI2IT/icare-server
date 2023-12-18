package com.ideas2It.icare.server.repository

import com.ideas2It.icare.server.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByUsernameAndIsDeletedFalse(username: String?) : User?
    
    fun findByUsername(username: String): User?
}