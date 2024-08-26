package com.pyj.ecommerceserver.domain.mailauth.repository

import com.pyj.ecommerceserver.domain.mailauth.MailAuthorizeHistory

interface MailAuthorizeHistoryRepository {
    fun save(mailAuthorizeHistory: MailAuthorizeHistory): MailAuthorizeHistory

    fun saveAll(mailAuthorizeHistories: List<MailAuthorizeHistory>)
}