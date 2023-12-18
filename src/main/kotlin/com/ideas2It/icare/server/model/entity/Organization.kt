package com.ideas2It.icare.server.model.entity

import com.ideas2It.icare.server.common.Constants
import jakarta.persistence.*

@Entity
@Table(name = Constants.TABLE_ORGANIZATION)
public data class Organization (
    @Column(name = Constants.FIELD_NGO_ID)
    val ngoId: String? = null,

    @Column(name = Constants.NAME)
    val name: String = "",

    @Column(name = Constants.FIELD_REGISTRATION_NUMBER)
    val registrationNumber: String? = null,

    @Column(name = Constants.FIELD_STATE)
    val state: String? = null,

    @Column(name = Constants.FIELD_CITY)
    val city: String? = null,

    @Column(name = Constants.FIELD_ADDRESS)
    val address: String? = null,

    @Column(name = Constants.EMAIL)
    var email : String? = null,

    @Column(name = Constants.FIELD_PHONE_NUMBER)
    val phoneNumber: String? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = Constants.USER_ID)
    var user: User = User()
) : BaseEntity()
