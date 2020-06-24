package com.project.evernote.service

import com.project.evernote.dataclass.NoteDTO
import com.project.evernote.model.Note
import com.project.evernote.model.User
import com.project.evernote.repository.NoteRepository
import org.springframework.stereotype.Service
import java.util.*

@Service("noteService")
class NoteService(val noteRepository: NoteRepository){

    fun saveNote(noteDTO: NoteDTO, user : User?) {
        if (noteDTO.name != null && noteDTO.description != null && user != null) {
            val users: MutableSet<User> = HashSet()
            users.add(user)
            noteRepository.save(Note(null, Date(), noteDTO.name, noteDTO.description, users))
        }
    }

    fun getNotesFromUser(user : User) : Set<Note?> {
        var users: MutableSet<User> = HashSet()
        users.add(user)
        return noteRepository.findByUsersIn(users) as MutableSet<Note?>
    }
}
