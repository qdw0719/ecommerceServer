package com.pyj.ecommerceserver.application.seller.command

class SellerCommand {

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
    )
}