package io.api.bloxy.core.dapp

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 19.01.2019
 */
class StatisticTests : BloxyTester() {

    @Test
    fun valid() {
        val result = api.dapp.statistics()
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].amount)
        assertNotNull(result[0].callers)
        assertNotNull(result[0].calls)
        assertNotNull(result[0].contractAddress)
        assertNotNull(result[0].contractType)
        assertNotNull(result[0].senders)
        assertNotNull(result[0].transfers)
        assertNotNull(result[0].toString())
    }
}