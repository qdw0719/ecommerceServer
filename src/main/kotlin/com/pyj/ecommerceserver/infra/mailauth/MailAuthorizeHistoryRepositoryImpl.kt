package com.pyj.ecommerceserver.infra.mailauth

import com.pyj.ecommerceserver.domain.mailauth.MailAuthorizeHistory
import com.pyj.ecommerceserver.domain.mailauth.repository.MailAuthorizeHistoryRepository
import org.springframework.stereotype.Repository

@Repository
class MailAuthorizeHistoryRepositoryImpl(
    private val mailAuthorizeHistoryJpaRepository: MailAuthorizeHistoryJpaRepository
): MailAuthorizeHistoryRepository {
    override fun save(mailAuthorizeHistory: MailAuthorizeHistory): MailAuthorizeHistory {
        return mailAuthorizeHistoryJpaRepository.save(mailAuthorizeHistory)
    }

    override fun saveAll(mailAuthorizeHistories: List<MailAuthorizeHistory>) {
        mailAuthorizeHistoryJpaRepository.saveAll(mailAuthorizeHistories)
    }
}