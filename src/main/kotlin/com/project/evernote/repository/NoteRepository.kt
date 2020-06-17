package com.project.evernote.repository

import com.project.evernote.model.Note
import com.project.evernote.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : JpaRepository<Note?, Long?> {
    fun findByName(name: String): Set<Note?>

}