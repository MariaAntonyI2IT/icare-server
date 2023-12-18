package com.ideas2It.icare.server.service.impl

import com.ideas2It.icare.server.common.Constants
import com.ideas2It.icare.server.model.dto.RegistrationRequestDTO
import com.ideas2It.icare.server.model.dto.ResponseDTO
import com.ideas2It.icare.server.model.dto.toContributor
import com.ideas2It.icare.server.model.entity.Contributor
import com.ideas2It.icare.server.model.entity.Organization
import com.ideas2It.icare.server.model.entity.Role
import com.ideas2It.icare.server.model.entity.User
import com.ideas2It.icare.server.repository.ContributorRepository
import com.ideas2It.icare.server.service.ContributorService
import com.ideas2It.icare.server.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

/**
 * This is a class that deals with the interaction between Contributor entity and the database
 */
@Service
class ContributorServiceImpl(
    private val contributorRepository: ContributorRepository,
    private val roleService: RoleService
) : ContributorService {

    /**
     * To check if a Contributor already exists with the same email ID
     */
    private fun isContributorExists(username: String?): Boolean {
        val isContributorExists = (null != contributorRepository.findByUser_Username(username));
        return isContributorExists;
    }

    /**
     * Registers a Contributor with the given data.
     */
    override fun registerContributor(request: RegistrationRequestDTO): ResponseDTO {
        if (isContributorExists(request.username)) {
            return ResponseDTO("An Contributor already exists with same email ID", null, HttpStatus.NOT_ACCEPTABLE)
        }
        val role: Role = roleService.findRoleByName(Constants.ROLE_CONTRIBUTOR);
        val user: User = User(request.username, request.password, role);
        val contributor: Contributor = request.toContributor();
        contributor.user = user
        return ResponseDTO("Contributor Registered Successfully", contributorRepository.save(contributor), HttpStatus.OK)
    }
}