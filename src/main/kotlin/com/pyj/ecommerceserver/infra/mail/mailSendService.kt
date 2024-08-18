package com.pyj.ecommerceserver.infra.mail

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun sendVerificationCode(email: String, verificationCode: String) {
        val title = "e-commerce 인증번호"
        val content = StringBuilder()
        content.append("<div style='font-family: malgun gothic;'>")
        content.append("    <h3>E-Commerce에서 보낸 인증번호 입니다.</h3>")
        content.append("    <div style='text-align: left; vertical-align: center;'>")
        content.append("        <div>인증번호를 비밀번호 초기화 화면에 입력해주세요.</div>")
        content.append("        <div>")
        content.append("            인증번호: <span style='font-weight: 700; color: blue; font-size: 20px;'>$verificationCode</span>")
        content.append("        </div>")
        content.append("    </div>")
        content.append("</div>")

        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        helper.setTo(email)
        helper.setSubject(title)
        helper.setText(content.toString(), true)

        javaMailSender.send(message)
    }
}