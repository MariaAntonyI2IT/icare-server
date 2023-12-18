package com.ideas2It.icare.server.model.entity

import com.ideas2It.icare.server.common.Constants
import jakarta.persistence.*

@Entity
@Table(name = Constants.TABLE_CONTRIBUTOR)
public data class Contributor(

    @Column(name = Constants.FIRST_NAME)
    var firstName: String = "",

    @Column(name = Constants.LAST_NAME)
    var lastName: String = "",

    @Column(name = Constants.FIELD_PHONE_NUMBER)
    val phoneNumber: String? = null,

    @Column(name = Constants.FIELD_AVATAR)
    var avatar: String? = null,

    @Column(name = Constants.EMAIL)
    var email : String = "",

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = Constants.USER_ID)
    var user: User = User()

) : BaseEntity()


