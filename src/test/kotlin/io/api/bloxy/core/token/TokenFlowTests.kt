package io.api.bloxy.core.token

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 08.06.2019
 */
class TokenFlowTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0xB97048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.flow(contract, limit = 5)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        mustValid(result[0].amount)
        mustValid(result[0].fromGroups)
        mayValid(result[0].fromGroupAddressesAnnotationsAsString)
        mustValid(result[0].fromGroupAddressesAsString)
        mustValid(result[0].fromGroupTypesAsString)
        mustValid(result[0].groupFromHash)
        mustValid(result[0].groupFromSize)
        mustValid(result[0].groupToHash)
        mustValid(result[0].groupToSize)
        mustValid(result[0].toGroups)
        mayValid(result[0].toGroupAddressesAnnotationsAsString)
        mustValid(result[0].toGroupAddressesAsString)
        mustValid(result[0].toGroupTypesAsString)
        mustValid(result[0].txCount)
        mustValid(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xB17048628DB6B661D4C2aA833e95Dbe1A905B280"
        val result = api.token.flow(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x97048628DB6B661D4C2aA833e95Dbe1A905B280"
        api.token.flow(contract)
    }
}