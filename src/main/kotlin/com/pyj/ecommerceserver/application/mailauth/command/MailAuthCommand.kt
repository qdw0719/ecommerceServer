package com.pyj.ecommerceserver.application.mailauth.command

import com.pyj.ecommerceserver.domain.common.enums.MailAuthType


class MailAuthCommand {
    data class UserAuthInfo(
        val userCode: String,
        val verificationCode: String,
        val type: MailAuthType
    )
}