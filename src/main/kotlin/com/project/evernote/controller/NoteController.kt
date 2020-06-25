package com.project.evernote.controller

import com.project.evernote.dataclass.NoteDTO
import com.project.evernote.service.NoteService
import com.project.evernote.service.UserServiceImpl
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
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

    @PutMapping("/editNote/{id}")
    fun edit(@RequestBody description : String?, @PathVariable id : Long, @AuthenticationPrincipal userDetails : UserDetails) : ModelAndView{
        val user = userService.findByEmail(userDetails.username)
        val modelAndView = ModelAndView()
        val note = noteService.findById(id)
        if (note!=null && description!=null && note.users != null && note.users.contains(user)){
            note.description = description
            noteService.noteRepository.save(note)
        }
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

    @GetMapping("/myNotes/delete/{id}")
    fun deleteNote(@PathVariable id : Long, @AuthenticationPrincipal userDetails : UserDetails) : ModelAndView{
        val modelAndView = ModelAndView()
        val user = userService.findByEmail(userDetails.username)
        var note = noteService.findById(id)
        if( note?.users != null && note.users!!.contains(user)){
            noteService.delete(id)
        }
        modelAndView.viewName = "redirect:/myNotes"
        return modelAndView
    }


}