package com.pyj.ecommerceserver.domain.mailauth.repository

import com.pyj.ecommerceserver.domain.common.enums.MailAuthType
import com.pyj.ecommerceserver.domain.mailauth.MailAuthorize

interface MailAuthorizeRepository {
    fun save(userAuthInfo: MailAuthorize)

    fun findUserAuthInfo(userCode: String, verificationCode: String): MailAuthorize?

    fun findByUserAuthInfo(userCode: String, type: MailAuthType): MailAuthorize
}