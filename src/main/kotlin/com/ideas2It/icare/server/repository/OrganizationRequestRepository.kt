package com.ideas2It.icare.server.repository

import com.ideas2It.icare.server.model.entity.OrganizationRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Status

@Repository
interface OrganizationRequestRepository : JpaRepository<OrganizationRequest, Long> {

    @Query(value = "select distinct request From OrganizationRequest as request left join fetch request.products as product where product.contributedBy is null")
    fun getRequestForContributor(): List<OrganizationRequest>
    @Query(value = "From OrganizationRequest as request left join fetch request.products as product where product.isAcknowledged =:status and request.organization.id = :orgId")
    fun getOrganizationRequest(@Param("orgId") orgId: Long, @Param("status") status: Boolean): List<OrganizationRequest>

    @Query(value = "select distinct request From OrganizationRequest as request left join fetch request.products as product where product.contributedBy.id = :contributedBy and product.isAcknowledged = :acknowledgedStatus")
    fun getProductsContributed(@Param("contributedBy") contributedBy : Long, @Param("acknowledgedStatus") acknowledgedStatus : Boolean): List<OrganizationRequest>
}