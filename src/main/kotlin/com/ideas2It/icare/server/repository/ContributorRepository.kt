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
interface ContributorRepository: JpaRepository<Contributor, Long> {

    fun findByUser_Username(username: String?): Contributor?

    @Query(value = "From Contributor as c where c.user.id=:userId")
    fun getContributorByUserId(@Param("userId") userId : Long): Contributor
}