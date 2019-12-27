package br.com.sample.controller

import br.com.sample.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val service: UserService) {

    @GetMapping("/users")
    suspend fun retrieveAll() = this.service.retrieveAll()

    @GetMapping("/conditionally/users")
    suspend fun retrieveAllConditionally(raiseError: Boolean = false) =
        this.service.retrieveAllConditionally(raiseError)

    @GetMapping("/timeout/users")
    suspend fun retrieveAllWithTimeout(timeout: Long = 2000) =
        this.service.retrieveAllWithTimeout(timeout)
}