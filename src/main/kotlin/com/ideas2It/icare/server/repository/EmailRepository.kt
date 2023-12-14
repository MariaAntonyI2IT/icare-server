package com.ideas2It.icare.server.repository

import com.ideas2It.icare.server.model.entity.OutBoundEmail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmailRepository : JpaRepository<OutBoundEmail, Long> {
    fun findByIsProcessedFalseAndRetryAttemptsLessThan(retryAttempts: Int): List<OutBoundEmail>
}