package com.ideas2It.icare.server.service.impl

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import com.ideas2It.icare.server.common.Constants
import com.ideas2It.icare.server.model.dto.RegistrationRequestDTO
import com.ideas2It.icare.server.model.dto.ResponseDTO
import com.ideas2It.icare.server.model.dto.toOrganization
import com.ideas2It.icare.server.model.entity.Organization
import com.ideas2It.icare.server.model.entity.Role
import com.ideas2It.icare.server.model.entity.User
import com.ideas2It.icare.server.repository.OrganizationRepository
import com.ideas2It.icare.server.repository.UserRepository
import com.ideas2It.icare.server.service.OrganizationService
import com.ideas2It.icare.server.service.RoleService
import org.apache.coyote.BadRequestException
import org.apache.poi.ss.usermodel.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.BadRequest
import java.io.*
import java.util.*

@Service
class OrganizationServiceImpl(
    private val organizationRepository: OrganizationRepository,
    private val roleService: RoleService, private val userRepository: UserRepository,
    private var orgIdPrefix: String = "IC-"
) : OrganizationService {

    override fun verifyNgoId(ngoId: String): ResponseDTO {
        if (isOrganizationExist(ngoId)) {
            return ResponseDTO("Organization already exists", null, HttpStatus.NOT_ACCEPTABLE)
        }

        val organizationDetails = getVerifiedOrganization(ngoId)
        println("=================" + organizationDetails)
        if (organizationDetails.isEmpty()) {
            return ResponseDTO("Your Organization is not an verified Organization", null, HttpStatus.NOT_ACCEPTABLE)
        }
        organizationDetails.set("userId", orgIdPrefix + (organizationDetails.get("ngoId")?.takeLast(4)))

        return ResponseDTO(" Organization verification completed. Please note the User Id.", organizationDetails, HttpStatus.OK)
    }

    private fun isOrganizationExist(ngoId: String): Boolean {
        return organizationRepository.existsByNgoId(ngoId)
    }

    override fun getVerifiedOrganization(ngoId: String): HashMap<String, String> {
        authorize()
        val formattedData = HashMap<String, HashMap<String, String>>();
//        var data = getData("19yZTIhvTntaxyRrgk59W-F2xa0DXP4HvH8ogQFSONH8", "Darpan_Organization_data", "A:B")
//        var data = getData("1S71EpkQUG4xnwE-lui91AbhBwVYEbwIcbobHDmQf-Xs", "Darpan_Organization_data", "A:B")
//        var data = getData("1G1oyIM_x0IUu6q-9AMlZLaZ4651oRVCQ5g7TPi9OkFQ", "Sheet1", "A2:B2")

        val data = getData("1cNHhS3ysx3ssmV4GvCN8cjp7bLmF9GNmfOlHRK_rXvA", "Sheet1", "A:H")
        for (row in data) {
            formattedData[row.get("ngoId") as String] = row;
        }
        var organizationDetails = HashMap<String, String>()
        if (formattedData.containsKey(ngoId)) {
            organizationDetails = formattedData.get(ngoId)!!
        }
        return organizationDetails
    }

    override fun registerOraganization(organizationRequest: RegistrationRequestDTO): ResponseDTO {
        val role: Role = roleService.findRoleByName(Constants.ROLE_ORG_USER);
        val userByUserName = userRepository.findByUsername(organizationRequest.username)
        if (null != userByUserName) {
            return ResponseDTO("Organization already exists with same User ID", null, HttpStatus.NOT_ACCEPTABLE)
        }
        println(role)
        val user: User = User(organizationRequest.username.lowercase(), organizationRequest.password, role);
        println(user)
        val organization: Organization = organizationRequest.toOrganization()
        organization.user = user
        println(organizationRequest)
        val savedOrganization: Organization = organizationRepository.save(organization)
        println(savedOrganization)
        return ResponseDTO("Organization Registered Successfully", savedOrganization, HttpStatus.OK)
    }


    private fun authorize(): Credential {
        val clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), FileReader("g-api/cred.json"))
        val scopes = Arrays.asList(SheetsScopes.SPREADSHEETS)
        val googleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(),
            clientSecrets,
            scopes
        )
            .setDataStoreFactory(FileDataStoreFactory(File("g-api")))
            .setAccessType("offline")
            .build()
        return AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, LocalServerReceiver()).authorize("user")
    }

    @Throws(java.lang.Exception::class)
    fun getData(
        spreadSheetId: String?,
        sheetName: String,
        rangeDataToRead: String
    ): ArrayList<HashMap<String, String>> {
        val sheet =
            Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), authorize())
        val data = sheet.spreadsheets().values()[spreadSheetId, "$sheetName!$rangeDataToRead"]
            .execute().getValues()
        val xlData = readExcel(data)
        return xlData;
    }

    fun readExcel(data: List<List<Any>>): ArrayList<HashMap<String, String>> {
        var sheetData: ArrayList<HashMap<String, String>> = ArrayList()
        if (data.isNotEmpty()) {
            var iterationCount = 0;
            val keys: ArrayList<String> = ArrayList()
            for (row in data) {
                val rowMap = HashMap<String, String>()
                var count = 0
                for (cell in row) {
                    if (iterationCount == 0) {
                        keys.add(cell as String);
                    } else {
                        rowMap[keys[count]] = (cell as String).trim()
                        count++
                    }

                }
                if (iterationCount != 0)
                    sheetData.add(rowMap)
                iterationCount++;
            }
        }
        return sheetData
    }
}