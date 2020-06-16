package com.project.evernote.service

import com.project.evernote.model.User

interface UserService {
    fun save(user: User)
    fun findById(id: Int): User?
    fun findByEmail(email: String?): User?
}