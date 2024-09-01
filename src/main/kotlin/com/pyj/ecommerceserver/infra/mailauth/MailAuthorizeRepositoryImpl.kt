package com.pyj.ecommerceserver.infra.mailauth

import com.pyj.ecommerceserver.domain.mailauth.MailAuthorize
import com.pyj.ecommerceserver.domain.mailauth.repository.MailAuthorizeRepository
import org.springframework.stereotype.Repository

@Repository
class MailAuthorizeRepositoryImpl(
    private val mailAuthorizeJpaRepository: MailAuthorizeJpaRepository
): MailAuthorizeRepository {
    override fun findUserAuthInfo(userCode: String): MailAuthorize? {
        return mailAuthorizeJpaRepository.findByUserCode(userCode)
    }

    override fun save(userAuthInfo: MailAuthorize) {
        mailAuthorizeJpaRepository.save(userAuthInfo)
    }
}