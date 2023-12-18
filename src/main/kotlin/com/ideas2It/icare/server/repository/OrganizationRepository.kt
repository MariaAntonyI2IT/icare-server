package com.ideas2It.icare.server.repository

import com.ideas2It.icare.server.model.entity.Contributor
import com.ideas2It.icare.server.model.entity.Organization
import com.ideas2It.icare.server.model.entity.Role
import com.ideas2It.icare.server.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository: JpaRepository<Organization, Long> {
    fun existsByNgoId(ngoId: String): Boolean

    fun findByUser_Username(username: String?): Organization?

    @Query(value = "From Organization as org where org.user.id=:userId")
    fun getOrgByUserId(@Param("userId") userId :Long) : Organization

}