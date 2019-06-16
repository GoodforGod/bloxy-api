package io.api.bloxy.core.makerdao

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 19.01.2019
 */
class PokeTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0x729d19f657bd0614b4985cf1d82531c67569197b"
        val result = api.makerDao.poke(contract, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].block)
        mustValid(result[0].from)
        mustValid(result[0].sender)
        mustValid(result[0].txHash)
        mustValid(result[0].txTime)
        mustValid(result[0].txTimeAsString)
        mustValid(result[0].value)
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