package com.project.evernote.controller

import com.project.evernote.dataclass.AccountDTO
import com.project.evernote.model.User
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
        if(accountDTO.email!= null && accountDTO.password != null && accountDTO.password_repeat != null && accountDTO.password == accountDTO.password_repeat  && userService.findByEmail(accountDTO.email) == null){
            userService.save(User(null,accountDTO.username,accountDTO.email,null,accountDTO.password,null,null))
            modelAndView.viewName = "registersuccess"
            return modelAndView
        }
        modelAndView.viewName = "register"
        return modelAndView
    }

    @GetMapping("/home")
    fun home (model: Model, @AuthenticationPrincipal userDetails : UserDetails): ModelAndView {
        val modelAndView = ModelAndView()
        val user = userService.findByEmail(userDetails.username)
        if(user!=null)
            modelAndView.addObject("greeting", "Hey "+ user.username + ", welcome back!")
        modelAndView.viewName = "home"
        return modelAndView
    }



    @RequestMapping(value = ["/login"], method = [RequestMethod.GET])
    fun loginForm(): ModelAndView? {
        return ModelAndView("login.html")
    }
}