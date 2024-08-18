package com.pyj.ecommerceserver.presentation.interceptor

import com.pyj.ecommerceserver.common.utils.JwtUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class JwtInterceptor: HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getParameter("token")
        if (token.isNullOrEmpty()) {
            throw RuntimeException("토큰이 없습니다.")
        }

        try {
            JwtUtil.validateToken(token)
        } catch (e: RuntimeException) {
            throw RuntimeException(e.message)
        }
        return true
    }
}