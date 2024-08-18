package com.pyj.ecommerceserver.application.user.facade

import com.pyj.ecommerceserver.application.user.command.UserCommand
import com.pyj.ecommerceserver.common.utils.JwtUtil
import com.pyj.ecommerceserver.domain.user.User
import com.pyj.ecommerceserver.domain.user.service.UserService
import com.pyj.ecommerceserver.infra.mail.EmailService
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userService: UserService,
    private val emailService: EmailService
) {

    fun login(userEmail: String, userPassword: String): User {
        val user = userService.login(userEmail, userPassword)
        user.setLastLoginTime()
        userService.saveLoginHistory(user)
        return user
    }

    fun sendVertifyCode(command: UserCommand.UserInfoByEmail): String {
        val user = userService.findUserInfoByEmail(command)
        val verificationCode = generateVerificationCode()
        val jwtToken = JwtUtil.generateToken(user.userCode, verificationCode)

        emailService.sendVerificationCode(user.userEmail, verificationCode)
        return jwtToken
    }

    fun resetPassword(token: String, inputVerificationCode: String, newPassword: String): User {
        if (!JwtUtil.validateToken(token)) {
            throw RuntimeException("인증시간이 초과되었습니다. 다시 시도해주세요.")
        }

        val storedVerificationCode = JwtUtil.getVerificationCode(token)
        if (storedVerificationCode != inputVerificationCode) {
            throw RuntimeException("인증번호가 일치하지 않습니다.")
        }

        val userCode = JwtUtil.getUserCode(token)
        val command = UserCommand.ChangeUserPassword(userCode, newPassword, newPassword)
        return userService.changeUserPassword(command)
    }

    private fun generateVerificationCode(): String {
        return (100000..999999).random().toString()
    }
}