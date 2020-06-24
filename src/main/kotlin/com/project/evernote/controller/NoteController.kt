package com.project.evernote.controller

import com.project.evernote.dataclass.NoteDTO
import com.project.evernote.service.NoteService
import com.project.evernote.service.UserServiceImpl
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

@Controller
@Lazy
class NoteController(var userService : UserServiceImpl,val noteService: NoteService){

    @PostMapping("/newNote")
    fun newNote(@ModelAttribute("noteDTO") @Valid noteDTO: NoteDTO, @AuthenticationPrincipal userDetails : UserDetails) : ModelAndView {
        val modelAndView = ModelAndView()
        val user = userService.findByEmail(userDetails.username)
        noteService.saveNote(noteDTO, user)
        modelAndView.viewName = "redirect:/myNotes"
        return modelAndView
    }

    @GetMapping("/myNotes")
    fun mynotes (model: Model, @AuthenticationPrincipal userDetails : UserDetails): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "myNotes"
        val user = userService.findByEmail(userDetails.username)
        if(user != null){
            modelAndView.addObject("notes", noteService.getNotesFromUser(user))
        }
        return modelAndView
    }

}