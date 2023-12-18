package com.ideas2It.icare.server.controller

import com.ideas2It.icare.server.model.dto.RequestDTO
import com.ideas2It.icare.server.model.entity.OrganizationRequest
import com.ideas2It.icare.server.service.InventoryService
import com.ideas2It.icare.server.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class InventoryController(private val inventoryService: InventoryService) {

    @PostMapping("/check")
    fun start() : ResponseEntity<Any>? {
        return ResponseEntity<Any>(inventoryService.getRequestsForOrg(5, false), HttpStatus.OK)
    }

    @PostMapping("/request/create")
    fun createRequest(@RequestBody organizationRequest: OrganizationRequest) : ResponseEntity<Any>? {
        inventoryService.createRequest(organizationRequest)

        return ResponseEntity<Any>(mapOf("message" to "Request created successfully"), HttpStatus.OK)
    }

    @PostMapping("/product-order")
    fun updateProductOrderDetails(@RequestBody request: RequestDTO) : ResponseEntity<Any>? {
        return ResponseEntity<Any>(inventoryService.updateProductOrdered(request), HttpStatus.OK)
    }

    @PostMapping("/product-acknowledge")
    fun updateProductAcknowledgeStatus(@RequestParam("productId") productId: Long) : ResponseEntity<Any>? {
        val result = inventoryService.updateProductAcknowledge(productId)
        return ResponseEntity<Any>(if (result) mapOf("message" to "Product acknowledged successfully") else mapOf("message" to "Product acknowledged failed"), HttpStatus.OK)
    }

    @PostMapping("/product-contributed/acknowledged")
    fun getProductsAcknowledged(@RequestParam("contributedId") contributedId :Long) : ResponseEntity<Any>? {
        return ResponseEntity<Any>(inventoryService.getProductsContributed(contributedId, true), HttpStatus.OK)
    }

    @PostMapping("/product-contributed/unacknowledged")
    fun getProductsUnAcknowledged(@RequestParam("contributedId") contributedId :Long) : ResponseEntity<Any>? {
        return ResponseEntity<Any>(inventoryService.getProductsContributed(contributedId, false), HttpStatus.OK)
    }

    @PostMapping("/request-list")
    fun getProductsForContributor() : ResponseEntity<Any>? {
        return ResponseEntity<Any>(inventoryService.getProductsForContributor(), HttpStatus.OK)
    }

    @PostMapping("/request/completed")
    fun getRequestCompleted(@RequestParam("orgId") orgId :Long) : ResponseEntity<Any>? {
        return ResponseEntity<Any>(inventoryService.getRequestsForOrg(orgId, true), HttpStatus.OK)
    }

    @PostMapping("/request/uncompleted")
    fun getRequestUnCompleted(@RequestParam("orgId") orgId :Long) : ResponseEntity<Any>? {
        return ResponseEntity<Any>(inventoryService.getRequestsForOrg(orgId, false), HttpStatus.OK)
    }



}
