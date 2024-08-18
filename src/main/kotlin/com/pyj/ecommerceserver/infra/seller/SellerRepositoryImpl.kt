package com.pyj.ecommerceserver.infra.seller

import com.pyj.ecommerceserver.domain.seller.Seller
import com.pyj.ecommerceserver.domain.seller.repository.SellerRepository
import org.springframework.stereotype.Repository

@Repository
class SellerRepositoryImpl(
    private val sellerJpaRepository: SellerJpaRepository
): SellerRepository {

    override fun save(seller: Seller): Seller {
        return sellerJpaRepository.save(seller)
    }

    override fun getSellerInfo(userCode: String): Seller {
        return sellerJpaRepository.findByUserCode(userCode)
    }

}