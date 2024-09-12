package com.pyj.ecommerceserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class EcommerceServerApplication

fun main(args: Array<String>) {
	runApplication<EcommerceServerApplication>(*args)
}
