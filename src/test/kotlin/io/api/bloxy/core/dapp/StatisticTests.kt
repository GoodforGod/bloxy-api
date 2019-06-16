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
        val result = api.dapp.statistics(limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mayValid(result[0].annotation)
        mayValid(result[0].amount)
        mustValid(result[0].callers)
        mustValid(result[0].calls)
        mustValid(result[0].contractAddress)
        mustValid(result[0].contractType)
        mayValid(result[0].senders)
        mayValid(result[0].transfers)
        mustValid(result[0].toString())
    }
}