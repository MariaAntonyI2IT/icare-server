package com.ideas2It.icare.server.model.entity

import com.ideas2It.icare.server.common.Constants
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "organization_request")
class OrganizationRequest: BaseEntity() {

    @Column(name = Constants.NAME)
    val name: String? = null

    @Column(name = Constants.DESCRIPTION)
    val description: String? = null

    @Column(name = Constants.TAG)
    val tag: String? = null

//    @Column(name = Constants.STATUS)
//    val status: String? = null

    @Column(name = Constants.RAISED_DATE)
    var raisedDate: Date? = null

    @ManyToOne
    @JoinColumn(name = Constants.ORGANIZATION_ID)
    var organization: Organization? = null

    @OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REQUEST_ID)
    var products: List<Product>? = null

    @Column(name = Constants.TYPE)
    val type : String? = null

    @Transient
    val orgId : Long? = null

}