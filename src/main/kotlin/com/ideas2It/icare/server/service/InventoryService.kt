package com.ideas2It.icare.server.service

import com.ideas2It.icare.server.model.dto.RequestDTO
import com.ideas2It.icare.server.model.entity.OrganizationRequest


interface InventoryService {
    fun getProductsForContributor() :List<OrganizationRequest>

    fun getProductsContributed(contributorId: Long, acknowledgedStatus : Boolean):List<OrganizationRequest>

    fun createRequest(request: OrganizationRequest)

    fun getRequestsForOrg(orgId: Long, status: Boolean): List<OrganizationRequest>

    fun updateProductAcknowledge(productId: Long): Boolean

    fun updateProductOrdered(request: RequestDTO) : String
}