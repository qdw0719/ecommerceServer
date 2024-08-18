package com.pyj.ecommerceserver.presentation.seller.dto

import com.pyj.ecommerceserver.domain.seller.Seller

class SellerResponseDTO{
    data class Regist(
        val userCode: String,
        val contact: String,
        val managerName: String,
        val address: String,
        val serialNumber: String
    ) {
        companion object {
            fun toResponse(seller: Seller): Regist {
                return Regist (
                    userCode = seller.userCode,
                    contact = seller.contact,
                    managerName = seller.managerName,
                    address = seller.address,
                    serialNumber = seller.serialNumber
                )
            }
        }
    }

    data class UpdateInfo(
        val userCode: String,
        val contact: String,
        val managerName: String,
        val address: String,
        val serialNumber: String
    ) {
        companion object {
            fun toResponse(seller: Seller): UpdateInfo {
                return UpdateInfo (
                    userCode = seller.userCode,
                    contact = seller.contact,
                    managerName = seller.managerName,
                    address = seller.address,
                    serialNumber = seller.serialNumber
                )
            }
        }
    }
}
