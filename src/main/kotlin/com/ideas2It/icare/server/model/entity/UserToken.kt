package com.ideas2It.icare.server.model.entity

import com.ideas2It.icare.server.common.Constants
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "user_token")
class UserToken(@Column(name = Constants.TOKEN) var token: String?, var tokenExpireTime: Date?) {
    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null

    constructor() : this(null, null)
}