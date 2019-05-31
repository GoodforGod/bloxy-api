package io.api.bloxy.core

import io.api.bloxy.core.impl.BloxyApi
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.model.dto.TokenType
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

    fun ifValid(value: Boolean) = assertTrue(value)
    fun ifValid(value: Long) = assertNotEquals(0L, value)
    fun ifValid(value: Int) = assertNotEquals(0, value)
    fun ifValid(value: String) = assertNotEquals("", value)
    fun ifValid(value: Double) = assertNotEquals(.0, value)
    fun ifValid(value: BigDecimal) = assertNotEquals(BigDecimal.ZERO, value)
    fun ifValid(value: LocalDate?) = assertNotNull(value)
    fun ifValid(value: LocalDateTime?) = assertNotNull(value)
    fun ifValid(value: AddressType) = assertNotEquals(AddressType.UNKNOWN, value)
    fun ifValid(value: TokenType) = assertNotEquals(TokenType.UNKNOWN, value)

    fun mayValid(value: Long) = assertNotNull(value)
    fun mayValid(value: Int) = assertNotNull(value)
    fun mayValid(value: String) = assertNotNull(value)
    fun mayValid(value: Double) = assertNotNull(value)
    fun mayValid(value: BigDecimal) = assertNotNull(value)
    fun mayValid(value: LocalDate?) = assertNotNull(value)
    fun mayValid(value: LocalDateTime?) = assertNotNull(value)
}