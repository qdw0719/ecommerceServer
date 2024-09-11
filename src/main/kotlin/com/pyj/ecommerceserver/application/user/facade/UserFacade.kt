package com.pyj.ecommerceserver.application.user.facade

import com.pyj.ecommerceserver.application.mailauth.command.MailAuthCommand
import com.pyj.ecommerceserver.application.user.command.UserCommand
import com.pyj.ecommerceserver.common.utils.JwtUtil
import com.pyj.ecommerceserver.domain.common.enums.MailAuthType
import com.pyj.ecommerceserver.domain.user.User
import com.pyj.ecommerceserver.domain.user.service.UserService
import com.pyj.ecommerceserver.domain.mailauth.service.EmailService
import com.pyj.ecommerceserver.domain.mailauth.service.MailAuthService
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userService: UserService,
    private val emailService: EmailService,
    private val mailAuthService: MailAuthService
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
        emailService.sendVerificationCode(user.userEmail, verificationCode)

        val userAuthInfo = MailAuthCommand.UserAuthInfo(user.userCode, verificationCode, MailAuthType.PasswordReset)
        mailAuthService.saveUserAuthorize(userAuthInfo)
        mailAuthService.createHistory(userAuthInfo)

        return "인증번호가 전송되었습니다.";
    }

    fun resetPassword(userEmail: String, inputVerificationCode: String, newPassword: String) {
        val userInfo = userService.findUserInfoByEmail(UserCommand.UserInfoByEmail(userEmail))
        val userAuthInfo = MailAuthCommand.UserAuthInfo(userInfo.userCode, inputVerificationCode, MailAuthType.PasswordReset)
        val userMailAuthInfo = mailAuthService.findByUserAuthInfo(userAuthInfo)?: throw RuntimeException("해당하는 유저가 존재하지 않습니다.: $userInfo.userCode")
        userMailAuthInfo.isValid()
        userMailAuthInfo.verificationCodeValidate(inputVerificationCode)

        val command = UserCommand.ChangeUserPassword(userCode = userInfo.userCode, newPassword = newPassword)
        userService.changeUserPassword(command)
    }

    private fun generateVerificationCode(): String {
        return (100000..999999).random().toString()
    }
}