package com.project.evernote.controller

import com.project.evernote.dataclass.AccountDTO
import com.project.evernote.model.User
import com.project.evernote.service.UserServiceImpl
import org.springframework.context.annotation.Lazy
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid


@Controller
@Lazy
class AuthenticationController(var userService : UserServiceImpl) {


    @GetMapping("/error")
    fun error (model: Model): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "error"
        return modelAndView
    }

    @GetMapping("/register")
    fun register (model: Model): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "register"
        return modelAndView
    }

    @RequestMapping("/createAccount")
    fun createAccount(@ModelAttribute("accountDTO") @Valid accountDTO: AccountDTO): ModelAndView {
        val modelAndView = ModelAndView()
        // save a single Customer
        if(accountDTO.email!= null && accountDTO.password != null && accountDTO.password_repeat != null && userService.findByEmail(accountDTO.email) == null){
            userService.save(User(null,accountDTO.email,accountDTO.email,null,accountDTO.password,null,null))
            modelAndView.viewName = "home"
            return modelAndView
        }
        modelAndView.viewName = "register"
        return modelAndView
    }

    @GetMapping("/home")
    fun home (model: Model): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "home"
        return modelAndView
    }

    @GetMapping("/myNotes")
    fun mynotes (model: Model): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "myNotes"
        return modelAndView
    }


    @RequestMapping(value = ["/login"], method = [RequestMethod.GET])
    fun loginForm(): ModelAndView? {
        return ModelAndView("login.html")
    }
}