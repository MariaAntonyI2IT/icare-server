package com.ideas2It.icare.server.service.impl

import com.ideas2It.icare.server.model.dto.RequestDTO
import com.ideas2It.icare.server.model.entity.OrganizationRequest
import com.ideas2It.icare.server.model.entity.Product
import com.ideas2It.icare.server.repository.ContributorRepository
import com.ideas2It.icare.server.repository.OrganizationRepository
import com.ideas2It.icare.server.repository.OrganizationRequestRepository
import com.ideas2It.icare.server.repository.ProductRepository
import com.ideas2It.icare.server.service.InventoryService
import org.springframework.stereotype.Service
import java.util.*

@Service
class InventoryServiceImpl(
        private val organizationRequestRepository: OrganizationRequestRepository,
        private val organizationRepository: OrganizationRepository,
        private val productRepository: ProductRepository,
        private val contributorRepository: ContributorRepository
): InventoryService {

    override fun createRequest(request: OrganizationRequest) {
        request.raisedDate = Date()
        request.organization = request.orgId?.let { organizationRepository.findById(it).get() }
        organizationRequestRepository.save(request)
    }

    override fun getRequestsForOrg(orgId: Long, status: Boolean): List<OrganizationRequest> {
        return organizationRequestRepository.getOrganizationRequest(orgId, status)
    }

    override fun updateProductAcknowledge(productId: Long): Boolean {
        val product = productRepository.findById(productId).get()
        if (product.contributedBy != null) {
            product.isAcknowledged = true
            productRepository.save(product)
            return true
        }
        return false
    }

    override fun updateProductOrdered(request: RequestDTO) : String {
        val product = request.productId?.let { productRepository.findById(it).get() }
        if (product != null) {
            product.contributedBy = request.contributorId?.let { contributorRepository.findById(it).get() }
            product.contributeDate = Date()
            product.orderPlatform = request.orderPlatform
            product.isOrderCompleted = true
            product.orderId = request.orderId
            productRepository.save(product)
            return "Product contribution successfully completed"
        }
        return "Product contribution failed"
    }

    override fun getProductsForContributor(): List<OrganizationRequest>{
        return organizationRequestRepository.getRequestForContributor()
    }

    override fun getProductsContributed(contributorId: Long, acknowledgedStatus : Boolean):List<OrganizationRequest> {
        return organizationRequestRepository.getProductsContributed(contributorId, acknowledgedStatus)
    }

}