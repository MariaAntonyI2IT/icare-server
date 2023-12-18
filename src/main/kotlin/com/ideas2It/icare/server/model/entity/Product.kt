package com.ideas2It.icare.server.model.entity

import com.ideas2It.icare.server.common.Constants
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = Constants.TABLE_PRODUCT)
class Product: BaseEntity() {

    @Column(name = Constants.NAME)
    val name: String? = null

    @Column(name = Constants.QUANTITY)
    val quantity: Long? = null

    @Column(name = Constants.UNIT)
    val unit: String? = null

    @Column(name = Constants.ORDER_PLATFORM)
    var orderPlatform: String? = null

    @Column(name = Constants.IS_ORDER_COMPLETED)
    var isOrderCompleted: Boolean = false

    @Column(name = Constants.ORDER_ID)
    var orderId: String? = null

    @ManyToOne
    @JoinColumn(name = Constants.ORDER_BY)
    var contributedBy: Contributor? = null

    @Column(name = Constants.ORDER_DATE)
    var contributeDate: Date? = null

    @Column(name = Constants.IS_ACKNOWLEDGED)
    var isAcknowledged: Boolean = false

    @Column(name = Constants.LINK)
    val link : String? = null

    @Column(name = Constants.REQUEST_ID)
    val requestId: Long? = null

}
