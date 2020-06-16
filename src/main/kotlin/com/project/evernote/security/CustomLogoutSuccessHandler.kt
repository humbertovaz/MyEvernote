package com.project.evernote.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class CustomLogoutSuccessHandler : SimpleUrlLogoutSuccessHandler(), LogoutSuccessHandler {
    // API
    @Throws(IOException::class, ServletException::class)
    override fun onLogoutSuccess(request: HttpServletRequest, response: HttpServletResponse?, authentication: Authentication?) {
        val refererUrl = request.getHeader("Referer")
        println(refererUrl)
        super.onLogoutSuccess(request, response, authentication)
    }
}