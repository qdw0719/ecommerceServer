package com.pyj.ecommerceserver.common.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.*

object JwtUtil {

    private val SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    private const val EXPIRATION_TIME: Long = 5 * 60 * 1000 // 5분

    fun generateToken(userCode: String, verificationCode: String): String {
        val claims = Jwts.claims().setSubject(userCode)
        claims["verificationCode"] = verificationCode // 인증번호를 클레임에 추가
        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = getClaims(token)
            return !claims.expiration.before(Date())
        } catch (e: ExpiredJwtException) {
            throw RuntimeException("인증시간이 초과되었습니다. 다시 시도해주세요.")
        } catch (e: Exception) {
            throw RuntimeException("유효하지 않은 토큰입니다.")
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
