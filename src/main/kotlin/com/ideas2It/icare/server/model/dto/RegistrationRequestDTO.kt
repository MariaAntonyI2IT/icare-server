package com.ideas2It.icare.server.model.dto

import com.ideas2It.icare.server.model.entity.Contributor
import com.ideas2It.icare.server.model.entity.Organization

data class RegistrationRequestDTO(

    val username: String = "",

    val password: String = "",

    val ngoId: String = "",

    val name: String = "",

    val registrationNumber: String? = null,

    val state: String? = null,

    val city: String? = null,

    val address: String? = null,

    val phoneNumber: String? = null,

    val email: String = "",

    var lastName: String = "",

    var firstName: String = "",

    var avatar: String? = null,

    )

fun RegistrationRequestDTO.toContributor(): Contributor {
    return Contributor(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        avatar = avatar
    )
}

fun RegistrationRequestDTO.toOrganization(): Organization {
    return Organization(
        ngoId = ngoId,
        name = name,
        registrationNumber = registrationNumber,
        state = state,
        city = city,
        address = address,
        email = email,
        phoneNumber = phoneNumber,
    )
}