package io.api.bloxy.core.dapp

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 19.01.2019
 */
class UsersTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0x105631c6cddba84d12fa916f0045b1f97ec9c268"
        val result = api.dapp.users(contract, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].haveFirstTxTime())
        mustValid(result[0].haveLastTxTime())
        mayValid(result[0].annotation)
        mayValid(result[0].multiSource)
        mustValid(result[0].calls)
        mustValid(result[0].firstTxTime)
        mustValid(result[0].lastTxTime)
        mustValid(result[0].firstTxAtAsString)
        mustValid(result[0].lastTxAtAsString)
        mayValid(result[0].receivedAmount)
        mayValid(result[0].sentAmount)
        mustValid(result[0].transfers)
        mustValid(result[0].toString())
    }

    @Test(expected = ParamException::class)
    fun `address not valid`() {
        val contract = "0x05631c6cddba84d12fa916f0045b1f97ec9c268"
        api.dapp.sources(contract)
    }

    @Test
    fun `address not exist`() {
        val contract = "0x205631c6cddba84d12fa916f0045b1f97ec9c268"
        val list = api.dapp.sources(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}