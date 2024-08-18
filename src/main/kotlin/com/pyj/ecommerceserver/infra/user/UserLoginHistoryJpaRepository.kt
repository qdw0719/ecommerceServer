package com.pyj.ecommerceserver.infra.user

import com.pyj.ecommerceserver.domain.user.UserLoginHistory
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoginHistoryJpaRepository: JpaRepository<UserLoginHistory, Long> {
}