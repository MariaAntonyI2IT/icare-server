package com.ideas2It.icare.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication()
@ComponentScan("com.ideas2It.icare")
class ICareServerApplication

fun main(args: Array<String>) {
	runApplication<ICareServerApplication>(*args)
}