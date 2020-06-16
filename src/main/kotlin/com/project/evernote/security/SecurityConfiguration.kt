package com.project.evernote.security

import com.project.evernote.service.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, // (1)
        securedEnabled = true, // (2)
        jsr250Enabled = true) // (3)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var customUserDetailsService: UserDetailsServiceImpl


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/performLogin")
                .permitAll()
                .antMatchers("/register")
                .permitAll()
                .antMatchers("/createAccount")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/login*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/performLogin")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                //.deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .csrf()
                .disable()

    }



    @Autowired
    @Throws(java.lang.Exception::class)
    fun configureGlobalSecurity(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
        auth.userDetailsService(customUserDetailsService)
        auth.authenticationProvider(authenticationProvider())
    }


    @Bean
    fun encoder() : PasswordEncoder = BCryptPasswordEncoder(11)

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider? {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService())
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }
}