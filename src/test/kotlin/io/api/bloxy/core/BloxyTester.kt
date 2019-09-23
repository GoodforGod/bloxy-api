package io.api.bloxy.core

import io.api.bloxy.core.impl.BloxyApi
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType
import io.api.bloxy.model.dto.DangerLevel
import io.api.bloxy.model.dto.TokenType
import io.api.bloxy.model.dto.address.CoinBalance
import io.api.bloxy.model.dto.dex.MethodType
import org.junit.Assert
import java.math.BigDecimal
import java.math.BigInteger
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

    fun mustValid(map: Map<*, *>) =
        assertTrue(map.any { it.key.toString().isNotBlank() && it.value.toString().isNotBlank() })

    fun mustValid(value: List<*>) = assertTrue(value.isNotEmpty() && value[0].toString().isNotBlank())
    fun mustValid(value: String) = assertTrue(value.isNotBlank() && value != "-")
    fun mustValid(value: Boolean) = assertTrue(value)
    fun mustValid(value: Long) = listOf(0, -1).forEach { assertNotEquals(it, value) }
    fun mustValid(value: Int) = listOf(0, -1).forEach { assertNotEquals(it, value) }
    fun mustValid(value: Double) = assertNotEquals(.0, value)
    fun mustValid(value: BigDecimal) = assertNotEquals(BigDecimal.ZERO, value)
    fun mustValid(value: BigInteger) = assertNotEquals(BigInteger.ZERO, value)
    fun mustValid(value: LocalDate?) = assertNotNull(value)
    fun mustValid(value: LocalDateTime?) = assertNotNull(value)
    fun mustValid(value: AddressType) = assertNotEquals(AddressType.UNKNOWN, value)
    fun mustValid(value: DangerLevel) = assertNotEquals(DangerLevel.UNKNOWN, value)
    fun mustValid(value: MethodType) = assertNotEquals(MethodType.UNKNOWN, value)
    fun mustValid(value: TokenType) = assertNotEquals(TokenType.UNKNOWN, value)
    fun mustValid(value: IModel?): () -> Unit = {
        assertNotNull(value)
        assertFalse(value?.isEmpty() ?: CoinBalance().isEmpty())
    }

    fun mayValid(value: Long) = assertNotNull(value)
    fun mayValid(value: Int) = assertNotNull(value)
    fun mayValid(value: String) = assertNotNull(value)
    fun mayValid(value: Double) = assertNotNull(value)
    fun mayValid(value: BigDecimal) = assertNotNull(value)
    fun mayValid(value: MethodType) = assertNotNull(value)
    fun mayValid(value: LocalDate?) { assertFalse(LocalDate.MIN == value) }
    fun mayValid(value: LocalDateTime?) { assertFalse(LocalDateTime.MIN == value)
    }
}