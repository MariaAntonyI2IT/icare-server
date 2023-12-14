package com.ideas2It.icare.server.repository

import com.ideas2It.icare.server.model.entity.Otp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OtpRepository : JpaRepository<Otp, Long> {
    fun findByUsernameAndIsVerifiedFalse(username: String): Otp?
    fun findByUsername(username: String): Otp?
}