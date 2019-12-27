package br.com.sample.service

import br.com.sample.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.runBlocking
import mu.KLogger
import mu.KotlinLogging.logger
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux.just
import java.time.Duration.ofSeconds

@Service
class UserService(
    private val cbFactory: ReactiveCircuitBreakerFactory<*, *>,
    private val log: KLogger = logger {}
) {

    fun retrieveAll() = listOf(
        User("User 1", 18),
        User("User 2", 25),
        User("User 3", 50)
    )

    suspend fun retrieveAllConditionally(raiseError: Boolean): Flow<User> {
        val flux = just(User("User 4", 18), User("User 5", 25), User("User 6", 50))
            .doFirst { if (raiseError) throw IllegalStateException("Exception raised!") }
        return this.cbFactory.create("retrieveAllConditionally").run(flux) {
            log.info { "m=retrieveAllConditionally raiseError=$raiseError it=$it" }
            just(*retrieveAll().toTypedArray())
        }.asFlow()
    }

    suspend fun retrieveAllWithTimeout(timeout: Long): Flow<User> {
        val flux = just(User("User 7", 18), User("User 8", 25), User("User 9", 50))
            .timeout(ofSeconds(30))
            .doOnSubscribe {
                runBlocking {
                    delay(timeout)
                }
            }
        return this.cbFactory.create("retrieveAllWithTimeout").run(flux) {
            log.info { "m=retrieveAllWithTimeout timeout=$timeout it=$it" }
            just(*retrieveAll().toTypedArray())
        }.asFlow()
    }
}