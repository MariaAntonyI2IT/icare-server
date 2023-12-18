package com.ideas2It.icare.server.authentication

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${cors.allowedMethods}")
    private lateinit var allowedMethods: String

    @Value("\${cors.allowedHeaders}")
    private lateinit var allowedHeaders: String

    @Value("\${cors.corsConfiguration}")
    private lateinit var corsConfiguration: String

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationSuccess(): AuthenticationSuccess {
        return AuthenticationSuccess()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        return AuthenticationProvider()
    }

    @Bean
    fun logoutSuccessHander() : LogoutHandler {
        return LogoutHandler()
    }

    @Bean
    fun failureHandler() : LoginFailure {
        return LoginFailure()
    }


    @Bean
    fun corsConfigurer() : WebMvcConfigurer {
        return object : WebMvcConfigurer{
            override fun addCorsMappings(configuration : CorsRegistry) {
                configuration.addMapping(corsConfiguration).allowedHeaders(allowedHeaders).allowedMethods(allowedMethods).exposedHeaders("*")
            }
        }
    }

    @Bean
    fun securityFilterChain(request: HttpSecurity): SecurityFilterChain {
        request
                .authorizeHttpRequests { requests ->
                    requests.anyRequest().permitAll()
                }
                .formLogin { form ->
                    form
                            .loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
                            .successHandler(authenticationSuccess()).failureHandler(failureHandler())
                }
                .exceptionHandling { exception ->
                    exception.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                }
                .logout { logout ->
                    logout
                            .logoutUrl("/logout").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                            .logoutSuccessHandler(logoutSuccessHander())
                }
                .csrf { csrf ->
                    csrf.disable().sessionManagement {session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    }
                }
//                .addFilterBefore(authFilter(), BasicAuthenticationFilter::class.java)

        return request.build()
    }
}
