package com.pyj.ecommerceserver.application.mailauth.facade

import com.pyj.ecommerceserver.domain.mailauth.MailAuthorize
import com.pyj.ecommerceserver.domain.mailauth.repository.MailAuthorizeRepository
import com.pyj.ecommerceserver.domain.mailauth.service.MailAuthService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MailAuthFacade(
    private val mailAuthService: MailAuthService,
    private val mailAuthorizeRepository: MailAuthorizeRepository
) {
    @Transactional
    fun invalidateExpiredAuthCodes(): List<MailAuthorize> {
        val expiredTargetList = mutableListOf<MailAuthorize>()  // 빈 리스트 초기화
        val getAllMailAuthorizeUsers = mailAuthService.getAllMailAuthorizeUsers()
        getAllMailAuthorizeUsers.forEach {
            if (it.isExpired()) {
                it.invalidate()
                expiredTargetList.add(it)
            }
        }
        return mailAuthorizeRepository.saveAll(expiredTargetList)
    }
}