package com.pyj.ecommerceserver.presentation.seller.dto

import com.pyj.ecommerceserver.application.seller.command.SellerCommand

class SellerRequestDTO {
    data class Regist(
        val userCode: String,
        val contact: String,
        val managerName: String,
        val address: String,
        val serialNumber: String
    )

    data class UpdateInfo(
        val userCode: String,
        val contact: String,
        val managerName: String,
        val address: String
    ) {
        fun toCommand(): SellerCommand.UpdateInfo {
            return SellerCommand.UpdateInfo(
                userCode = userCode,
                contact = contact,
                managerName = managerName,
                address =  address
            )
        }
    }
}