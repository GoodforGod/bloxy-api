package io.api.bloxy.core.address

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.06.2019
 */
class AllTests : BloxyTester() {

    @Test
    fun valid() {
        val result = api.address.all(limit = 10)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mustValid(result[0].addrTypeAsString)
        mustValid(result[0].addrType)
        mayValid(result[0].addressAnnotation)
        mustValid(result[0].balance)
        mustValid(result[0].callsFromCount)
        mayValid(result[0].transfersFromCount)
        mustValid(result[0].toString())
    }
}