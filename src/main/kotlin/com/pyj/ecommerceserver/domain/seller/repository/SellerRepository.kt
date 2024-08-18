package com.pyj.ecommerceserver.domain.seller.repository

import com.pyj.ecommerceserver.domain.seller.Seller

interface SellerRepository {
    fun save(seller: Seller): Seller

    fun getSellerInfo(userCode: String): Seller
}