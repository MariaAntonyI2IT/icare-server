package com.ideas2It.icare.server.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import java.io.Serializable

@Entity
@Table(name = "role")
data class Role(

        @Column(name = "name")
        var name: String? = null,

        @Transient
        private val authority: String? = null
): BaseEntity(), Serializable, GrantedAuthority {

    override fun getAuthority(): String? {
        return name
    }
}
