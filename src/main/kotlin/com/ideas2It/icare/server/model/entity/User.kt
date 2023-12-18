package com.ideas2It.icare.server.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.ideas2It.icare.server.common.Constants
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnTransformer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

@Entity
@Table(name = "\"user\"")
data class User(

        @Column(name = Constants.USERNAME)
        private val username: String = "",

        @Column(name = Constants.PASSWORD)
        @ColumnTransformer(forColumn = "", read = "public.pgp_sym_decrypt(password::bytea, " + "'icare')"
                , write = "public.pgp_sym_encrypt(?, " + "'icare')")
        private val password: String? = null,

        @ManyToOne
        @JoinColumn(name = Constants.ROLE_ID)
        val role: Role = Role()
) : BaseEntity(), Serializable, UserDetails {

    @JsonIgnore
    override fun getAuthorities(): Set<GrantedAuthority> {
        val authorities: MutableSet<GrantedAuthority> = LinkedHashSet()
        authorities.add(role)
        return authorities
    }

    @JsonIgnore
    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = super.isActive
}
