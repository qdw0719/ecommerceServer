package com.pyj.ecommerceserver.domain.seller

import com.pyj.ecommerceserver.domain.common.enums.ValidState
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Seller(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userCode: String,
    var contact: String,
    var managerName: String,
    var address: String,
    var serialNumber: String,
    @Enumerated(EnumType.STRING)
    var validState: ValidState,
    var createAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {

    companion object{
        fun create(
            userCode: String,
            contact: String,
            managerName: String,
            address: String,
            serialNumber: String
        ): Seller {
            require(userCode.isNotEmpty()) { "유저코드가 누락되었습니다. 로그인상태를 확인해주세요." }
            require(serialNumber.isNotEmpty()) { "사업장번호는 필수사항입니다." }
            return Seller(
                userCode = userCode,
                contact = contact,
                managerName = managerName,
                address = address,
                serialNumber = serialNumber,
                validState = ValidState.Valid,
                createAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }

    @PostUpdate fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }

    fun updateSellerInfo(
        contact: String,
        managerName: String,
        address: String
    ) {
        this.contact = contact
        this.managerName = managerName
        this.address = address
    }

    // todo::
    //       판매자 철수에 대한 내용 필요 (유저타입 normal로 변경)
}
