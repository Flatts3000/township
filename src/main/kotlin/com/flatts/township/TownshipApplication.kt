package com.flatts.township

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
class TownshipApplication

fun main(args: Array<String>) {
    runApplication<TownshipApplication>(*args)
}
