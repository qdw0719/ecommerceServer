package com.pyj.ecommerceserver.common.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.*

object JwtUtil {

    private val SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    private val EXPIRATION_TIME: Long = 5 * 60 * 1000 // 5분

    fun generateToken(userCode: String, verificationCode: String): String {
        val claims = Jwts.claims().setSubject(userCode)
        claims["verificationCode"] = verificationCode
        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    fun validateToken(token: String) {
        if (token.isNullOrEmpty()) {
            throw RuntimeException("토큰이 없습니다.")
        }
        try {
            val claims = getClaims(token)
        } catch (e1: ExpiredJwtException) {
            throw RuntimeException("인증시간이 초과되었습니다. 다시 시도해주세요.", e1)
        } catch (e2: Exception) {
            throw RuntimeException("유효하지 않은 토큰입니다.", e2)
        }
    }

    fun getUserCode(token: String): String {
        return getClaims(token).subject
    }

    fun getVerificationCode(token: String): String {
        return getClaims(token)["verificationCode"] as String
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .body
    }
}
