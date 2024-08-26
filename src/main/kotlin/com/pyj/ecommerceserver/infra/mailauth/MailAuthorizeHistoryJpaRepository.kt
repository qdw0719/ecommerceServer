package com.pyj.ecommerceserver.infra.mailauth

import com.pyj.ecommerceserver.domain.mailauth.MailAuthorizeHistory
import org.springframework.data.jpa.repository.JpaRepository

interface MailAuthorizeHistoryJpaRepository: JpaRepository<MailAuthorizeHistory, Long> {
}