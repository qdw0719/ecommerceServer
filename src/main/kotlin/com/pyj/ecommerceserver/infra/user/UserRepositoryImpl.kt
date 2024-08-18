package com.pyj.ecommerceserver.infra.user

import com.pyj.ecommerceserver.domain.user.User
import com.pyj.ecommerceserver.domain.user.UserLoginHistory
import com.pyj.ecommerceserver.domain.user.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
    private val userLoginHistoryJpaRepository: UserLoginHistoryJpaRepository
): UserRepository {

    override fun findUser(userCode: String): User? {
        return userJpaRepository.findByUserCode(userCode)
    }

    override fun regist(user: User): User {
        return userJpaRepository.save(user)
    }

    override fun save(user: User): User {
        return userJpaRepository.save(user)
    }

    override fun findUserByEmail(userEmail: String): User? {
        return userJpaRepository.findByUserEmail(userEmail)
    }

    override fun loginHistorySave(userLoginHistory: UserLoginHistory) {
        userLoginHistoryJpaRepository.save(userLoginHistory)
    }
}