package com.pyj.ecommerceserver.domain.seller.service

import com.pyj.ecommerceserver.application.seller.command.SellerCommand
import com.pyj.ecommerceserver.application.seller.facade.SellerFacade
import com.pyj.ecommerceserver.application.user.command.UserCommand
import com.pyj.ecommerceserver.domain.user.UserType
import com.pyj.ecommerceserver.domain.user.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SellerServiceTest(
    @Autowired val sellerService: SellerService,
    @Autowired val sellerFacade: SellerFacade,
    @Autowired val userService: UserService
) {

    @BeforeEach fun setup() {}

    @AfterEach fun teardown() {}


    @Test fun `판매자 등록 테스트`() {
        // given
        val userCode = "2549dbe8-2eee-41bd-89f3-6f3a422b2ef9" // nick = testUser123
        val contact = "02-0123-4567"
        val managerName = "김이박최"
        val address = "서울시 태헤란로 1길"
        val serialNumber = "123-44-56789"

        // when
        val newSeller = sellerFacade.regist(userCode = userCode, contact = contact, managerName = managerName, address = address, serialNumber = serialNumber)

        // then
        val userInfo = userService.findUserInfo(UserCommand.UserInfo(userCode))
        assertThat(newSeller).isNotNull()
        assertThat(newSeller.userCode).isEqualTo(userInfo.userCode)
        assertThat(userInfo.userType).isEqualTo(UserType.Seller)
    }
}