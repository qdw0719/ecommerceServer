package com.pyj.ecommerceserver.infra.mailauth

import com.pyj.ecommerceserver.domain.common.enums.MailAuthType
import com.pyj.ecommerceserver.domain.mailauth.MailAuthorize
import org.springframework.data.jpa.repository.JpaRepository

interface MailAuthorizeJpaRepository: JpaRepository<MailAuthorize, Long> {
    fun findByUserCode(userCode: String): MailAuthorize?

    fun findByUserCodeAndType(userCode: String, type: MailAuthType): MailAuthorize
}