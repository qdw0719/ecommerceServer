package com.pyj.ecommerceserver.domain.mailauth.repository

import com.pyj.ecommerceserver.domain.mailauth.MailAuthorize

interface MailAuthorizeRepository {
    fun save(userAuthInfo: MailAuthorize)

    fun saveAll(expiredTargetList: List<MailAuthorize>): List<MailAuthorize>

    fun findUserAuthInfo(userCode: String): MailAuthorize?

    fun getAllMailAuthorizeUsers(): List<MailAuthorize>

    fun findUserAuthInfoByValid(userCode: String): MailAuthorize?
}