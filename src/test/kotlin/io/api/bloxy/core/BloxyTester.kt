package io.api.bloxy.core

import io.api.bloxy.core.impl.BloxyApi
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.model.dto.DangerLevel
import io.api.bloxy.model.dto.TokenType
import io.api.bloxy.model.dto.address.CoinBalance
import org.junit.Assert
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * Test extension class with bloxy api initialized by api key
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class BloxyTester : Assert() {
    val key by lazy { System.getenv("BLOXY_API").ifEmpty { throw NullPointerException("API KEY IS NULL") } }
    val api by lazy { BloxyApi(key) }

    fun mustValid(value: String) = listOf("", "-").forEach { assertNotEquals(it, value) }
    fun mustValid(value: Boolean) = assertTrue(value)
    fun mustValid(value: Long) = assertNotEquals(0L, value)
    fun mustValid(value: Int) = assertNotEquals(0, value)
    fun mustValid(value: Double) = assertNotEquals(.0, value)
    fun mustValid(value: BigDecimal) = assertNotEquals(BigDecimal.ZERO, value)
    fun mustValid(value: LocalDate?) = assertNotNull(value)
    fun mustValid(value: LocalDateTime?) = assertNotNull(value)
    fun mustValid(value: CoinBalance?) = assertNotNull(value)
    fun mustValid(value: AddressType) = assertNotEquals(AddressType.UNKNOWN, value)
    fun mustValid(value: TokenType) = assertNotEquals(TokenType.UNKNOWN, value)
    fun mustValid(value: DangerLevel) = assertNotEquals(DangerLevel.UNKNOWN, value)

    fun mayValid(value: Long) = assertNotNull(value)
    fun mayValid(value: Int) = assertNotNull(value)
    fun mayValid(value: String) = assertNotNull(value)
    fun mayValid(value: Double) = assertNotNull(value)
    fun mayValid(value: BigDecimal) = assertNotNull(value)
    fun mayValid(value: LocalDate?) {}
    fun mayValid(value: LocalDateTime?) {}
}