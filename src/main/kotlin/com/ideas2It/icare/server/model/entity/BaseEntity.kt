package com.ideas2It.icare.server.model.entity

import com.ideas2It.icare.server.common.Constants
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.util.*

@MappedSuperclass
open class BaseEntity: Serializable {

    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null

    @Column(name = Constants.CREATED_AT)
    @CreationTimestamp
    val createdAt : Date? = null

    @Column(name = Constants.UPDATED_AT)
    @UpdateTimestamp
    val  updateAt : Date? = null

    @Column(name = Constants.UPDATED_BY)
    val updateBy : Long? = null

    @Column(name = Constants.CREATED_BY)
    val  createdBy : Long? = null

    @Column(name = Constants.IS_DELETED)
    val isDeleted : Boolean = false

    @Column(name = Constants.IS_ACTIVE)
    val isActive : Boolean = true



}