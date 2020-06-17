package com.project.evernote.controller

import com.project.evernote.dataclass.NoteDTO
import com.project.evernote.repository.NoteRepository
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import kotlin.collections.ArrayList


@RestController
class RestController(val repository: NoteRepository) {

    @GetMapping("/")
    fun home (model: Model): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "home"
        return modelAndView
    }


    @GetMapping("/getNotes")
    fun getNotes() : List<NoteDTO>{
        val notes = repository.findAll()
        val notesUI: MutableList<NoteDTO> = ArrayList<NoteDTO>()
        for (note in notes) {
            if (note != null) {
                notesUI.add(NoteDTO(note.name, note.description))
            }
        }
        return notesUI
        }
}