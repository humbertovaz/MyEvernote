package com.project.evernote.service

import com.project.evernote.dataclass.NoteDTO
import com.project.evernote.model.Note
import com.project.evernote.model.User

import com.project.evernote.repository.UserRepository
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service("userService")
@Transactional
class UserServiceImpl (val encoder: PasswordEncoder,val userRepository: UserRepository ) : UserService  {

    override fun save(user: User) {
        user.password = encoder.encode(user.password)
        userRepository.save(user)
    }

    override fun findById(id: Int): User? {
        return userRepository.findById(id)
    }

    override fun findByEmail(email: String?): User? {
        return userRepository.findByEmail(email!!)
    }
}