package com.project.evernote.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service("userDetailsServiceImpl")
class UserDetailsServiceImpl  : UserDetailsService {

    @Autowired
    lateinit var userService: UserService

    @Transactional(readOnly = true)
    @Throws(Exception::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val activeUserInfo: com.project.evernote.model.User? = userService.findByEmail(username)
        val dBuserName: String = activeUserInfo?.email ?: throw Exception("User not authorized.")
        val authority: GrantedAuthority = SimpleGrantedAuthority("user")
        return User(dBuserName,
                activeUserInfo.password, listOf(authority)) as UserDetails
    }

}