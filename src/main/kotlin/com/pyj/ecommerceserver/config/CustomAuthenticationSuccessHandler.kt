package com.pyj.ecommerceserver.config

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.http.HttpStatus
import com.fasterxml.jackson.databind.ObjectMapper
import com.pyj.ecommerceserver.domain.user.User
import com.pyj.ecommerceserver.presentation.user.dto.UserResponseDTO
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationSuccessHandler: AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val user = authentication.principal as User

        val userResponse = UserResponseDTO.Regist(
            user.userCode,
            user.userEmail,
            user.nickname
        )

        response.status = HttpStatus.OK.value()
        response.contentType = "application/json"
        val out = response.writer
        val mapper = ObjectMapper()
        mapper.writeValue(out, userResponse)
        out.flush()
    }
}