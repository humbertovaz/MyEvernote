package com.project.evernote.controller

import com.project.evernote.dataclass.NoteDTO
import com.project.evernote.model.Note
import com.project.evernote.model.User
import com.project.evernote.repository.NoteRepository
import com.project.evernote.service.UserServiceImpl
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.validation.Valid

@Controller
@Lazy
class NoteController(var userService : UserServiceImpl, val noteRepository: NoteRepository){

    @PostMapping("/newNote")
    fun newNote(@ModelAttribute("noteDTO") @Valid noteDTO: NoteDTO, @AuthenticationPrincipal userDetails : UserDetails) : ModelAndView {
        val modelAndView = ModelAndView()
        val user = userService.findByEmail(userDetails.username)
        if(noteDTO.name != null && noteDTO.description != null && user != null){
            val users: MutableSet<User> = HashSet()
            users.add(user)
            noteRepository.save(Note(null, Date(),noteDTO.name,noteDTO.description,users))
        }
        modelAndView.viewName = "myNotes"
        return modelAndView
    }

}