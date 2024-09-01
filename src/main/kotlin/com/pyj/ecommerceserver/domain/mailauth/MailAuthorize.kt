package com.pyj.ecommerceserver.domain.mailauth

import com.pyj.ecommerceserver.domain.common.enums.ValidState
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class MailAuthorize(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userCode: String,
    var verificationCode: String,
    var sendCount: Int,
    @Enumerated(EnumType.STRING)
    var validState: ValidState,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {
    companion object {
        fun create(userCode: String, verificationCode: String, sendCount: Int): MailAuthorize {
            require(userCode.isNotEmpty()) { "유저고유 식별코드가 누락되었습니다." }
            return MailAuthorize(
                userCode = userCode,
                verificationCode = verificationCode,
                sendCount = sendCount,
                validState = ValidState.Valid,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }

    @PostUpdate fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }

    fun validate() {
        validState = ValidState.Valid
    }

    fun invalidate() {
        validState = ValidState.Invalid
    }

    fun validation() {
        if (validState == ValidState.Invalid) {
            throw RuntimeException("유효한 인증번호가 아닙니다.")
        }
    }

    fun isValid() {
        if (validState != ValidState.Valid) {
            throw RuntimeException("인증번호가 유효하지 않습니다. 재발급 후 인증해주세요.")
        }
    }

    fun increaseSendCount() {
        sendCount = sendCount + 1
    }

    fun newVerificationCode(newVerificationCode: String) {
        verificationCode = newVerificationCode
    }
}
