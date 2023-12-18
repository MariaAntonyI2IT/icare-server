package com.ideas2It.icare.server.model.dto

import com.ideas2It.icare.server.model.entity.Contributor
import com.ideas2It.icare.server.model.entity.Organization

data class ProfileResponse(
        val username : String,
        var contributor: Contributor?,
        var organization: Organization?
)
