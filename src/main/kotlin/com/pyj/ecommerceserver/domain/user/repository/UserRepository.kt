package com.pyj.ecommerceserver.domain.user.repository

import com.pyj.ecommerceserver.domain.user.User
import com.pyj.ecommerceserver.domain.user.UserLoginHistory

interface UserRepository {
    fun findUser(userCode: String): User?

    fun regist(user: User): User

    fun save(user: User): User

    fun findUserByEmail(userEmail: String): User?

    fun loginHistorySave(userLoginHistory: UserLoginHistory)
}