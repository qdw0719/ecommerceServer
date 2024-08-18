package com.pyj.ecommerceserver.application.seller.facade

import com.pyj.ecommerceserver.application.seller.command.SellerCommand
import com.pyj.ecommerceserver.domain.seller.Seller
import com.pyj.ecommerceserver.domain.seller.service.SellerService
import com.pyj.ecommerceserver.domain.user.service.UserService
import org.springframework.stereotype.Component

@Component
class SellerFacade(
    private val sellerService: SellerService,
    private val userService: UserService
) {
    fun regist(
        userCode: String,
        contact: String,
        managerName: String,
        address: String,
        serialNumber: String
    ): Seller {
        userService.userToSeller(userCode)
        return sellerService.regist(SellerCommand.Regist(userCode, contact, managerName, address, serialNumber))
    }
}