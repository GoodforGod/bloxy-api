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
        val result = api.dapp.users(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveFirstTxTime())
        assertTrue(result[0].haveLastTxTime())
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].multiSource)
        assertNotNull(result[0].annotation)
        assertNotNull(result[0].calls)
        assertNotNull(result[0].firstTxTime)
        assertNotNull(result[0].lastTxTime)
        assertNotNull(result[0].firstTxAtAsString)
        assertNotNull(result[0].lastTxAtAsString)
        assertNotNull(result[0].receivedAmount)
        assertNotNull(result[0].sentAmount)
        assertNotNull(result[0].transfers)
        assertNotNull(result[0].toString())
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