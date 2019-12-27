package br.com.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CircuitBreakerSampleApplication

fun main(args: Array<String>) {
    runApplication<CircuitBreakerSampleApplication>(*args)
}
