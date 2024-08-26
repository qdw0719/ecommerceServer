package com.pyj.ecommerceserver.config

import com.pyj.ecommerceserver.presentation.interceptor.JwtInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    @Autowired private val jwtInterceptor: JwtInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/users/auth/reset-password")
    }
}
