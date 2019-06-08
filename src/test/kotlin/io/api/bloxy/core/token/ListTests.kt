package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 08.06.2019
 */
class ListTests : BloxyTester() {

    @Test
    fun valid() {
        val result = api.token.list(limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].address)
        mustValid(result[0].name)
        mustValid(result[0].symbol)
        mustValid(result[0].typeAsString)
        mustValid(result[0].txs)
        mustValid(result[0].tokenType)
        mustValid(result[0].toString())
    }
}