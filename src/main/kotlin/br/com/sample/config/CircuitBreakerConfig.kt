package br.com.sample.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.ofDefaults
import io.github.resilience4j.timelimiter.TimeLimiterConfig.custom
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration.ofSeconds
import java.util.function.Consumer


@Configuration
class CircuitBreakerConfig {

    @Bean
    fun retrieveAllWithTimeoutCustomizer(): Customizer<ReactiveResilience4JCircuitBreakerFactory> =
        Customizer { factory ->
            factory.configure(Consumer { builder ->
                builder.timeLimiterConfig(custom().timeoutDuration(ofSeconds(5)).build())
                    .circuitBreakerConfig(ofDefaults())
            }, "retrieveAllWithTimeout")
        }
}