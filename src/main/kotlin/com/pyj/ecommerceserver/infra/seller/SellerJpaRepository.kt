package com.pyj.ecommerceserver.infra.seller

import com.pyj.ecommerceserver.domain.seller.Seller
import org.springframework.data.jpa.repository.JpaRepository

interface SellerJpaRepository: JpaRepository<Seller, Long> {
    fun findByUserCode(userCode: String): Seller
}