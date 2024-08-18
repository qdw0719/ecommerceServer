package com.pyj.ecommerceserver.domain.seller.service

import com.pyj.ecommerceserver.application.seller.command.SellerCommand
import com.pyj.ecommerceserver.domain.seller.Seller
import com.pyj.ecommerceserver.domain.seller.repository.SellerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SellerService(
    private val sellerRepository: SellerRepository
) {

    @Transactional
    fun regist(command: SellerCommand.Regist): Seller {
        val seller = Seller.create(command.userCode, command.contact, command.managerName, command.address, command.serialNumber)
        return sellerRepository.save(seller)
    }

    @Transactional
    fun updateInfo(command: SellerCommand.UpdateInfo): Seller {
        val seller = sellerRepository.getSellerInfo(command.userCode)
        seller.updateSellerInfo(command.contact, command.managerName, command.address)
        return sellerRepository.save(seller)
    }
}