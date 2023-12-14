package com.ideas2It.icare.server.model.entity

import com.ideas2It.icare.server.common.Constants
import jakarta.persistence.*

@Entity
@Table(name = "otp")
data class Otp (
        @Id
        @Column(name = Constants.ID)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = Constants.USERNAME)
        val username: String? = null,

        @Column(name = Constants.OTP)
        var otp: String? = null,

        @Column(name = "is_verified")
        var isVerified: Boolean = false
)