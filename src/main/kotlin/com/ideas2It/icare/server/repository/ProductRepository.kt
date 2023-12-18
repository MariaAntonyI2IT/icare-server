package com.ideas2It.icare.server.repository

import com.ideas2It.icare.server.model.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

//    fun findByOrganizationId(organizationId: Long) : List<Product>
//    @Query(value = "From Product as product where product.orderBy is not null")
//    fun getProductsForContributor(): List<Product>
}