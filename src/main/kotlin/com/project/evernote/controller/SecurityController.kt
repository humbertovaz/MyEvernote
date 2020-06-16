package com.project.evernote.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.security.Principal
import javax.servlet.http.HttpServletRequest


@Controller
class SecurityController {
    @RequestMapping(value = ["/username"], method = [RequestMethod.GET])
    @ResponseBody
    open fun currentUserNameSimple(request: HttpServletRequest): String? {
        val principal: Principal = request.userPrincipal
        return principal.name
    }
}