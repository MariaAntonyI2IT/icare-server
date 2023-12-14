package com.ideas2It.icare.server.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import jakarta.persistence.*

@Entity
@Table(name = "out_bound_email")
data class OutBoundEmail (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(
        TemporalType.TIMESTAMP
    )
    val createdAt: Date? = null,

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    val updatedAt: Date? = null,

    @Column(name = "is_processed")
    var isProcessed: Boolean = false,

    @Column(name = "retry_attempts")
    var retryAttempts: Int = 0,

    @Column(name = "to_address")
    val toAddress: String? = null,

    @Column(name = "subject")
    val subject: String? = null,

    @Column(name = "body")
    val body: String? = null
)