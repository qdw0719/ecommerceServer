package com.pyj.ecommerceserver.presentation.seller.controller

import com.pyj.ecommerceserver.application.seller.command.SellerCommand
import com.pyj.ecommerceserver.application.seller.facade.SellerFacade
import com.pyj.ecommerceserver.domain.seller.service.SellerService
import com.pyj.ecommerceserver.presentation.seller.dto.SellerRequestDTO
import com.pyj.ecommerceserver.presentation.seller.dto.SellerResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController @RequestMapping("/api/seller")
class SellerController(
    private val sellerFacade: SellerFacade,
    private val sellerService: SellerService
) {

    @PostMapping("/regist")
    fun registerSeller(@RequestBody request: SellerRequestDTO.Regist): ResponseEntity<SellerResponseDTO.Regist> {
        return ResponseEntity.ok(
            SellerResponseDTO.Regist.toResponse(sellerFacade.regist(request.userCode, request.contact, request.managerName, request.address, request.serialNumber))
        )
    }

    @PostMapping("/update-info")
    fun updateInfo(@RequestBody request: SellerRequestDTO.UpdateInfo): ResponseEntity<SellerResponseDTO.UpdateInfo> {
        return ResponseEntity.ok(
            SellerResponseDTO.UpdateInfo.toResponse(sellerService.updateInfo(request.toCommand()))
        )
    }
}