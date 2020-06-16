package com.project.evernote.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView


@RestController
class HtmlController {

    @GetMapping("/")
    fun home (model: Model): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "home"
        return modelAndView
    }

}