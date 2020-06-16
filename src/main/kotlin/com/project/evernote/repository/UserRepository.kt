package com.project.evernote.repository


import com.project.evernote.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User?, Long?> {
    fun findByEmail(email: String): User?
    fun findById(id: Int) : User?


}