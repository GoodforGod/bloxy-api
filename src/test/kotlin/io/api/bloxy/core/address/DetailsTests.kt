package io.api.bloxy.core.address

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class DetailsTests : BloxyTester() {

//    @Test
    fun valid() {
        val list = listOf("0x9eAb08daA285183F9A04269747D4125F08e634B0", "0x16f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        val details = api.address.details(list)
        assertNotNull(details)
        assertFalse(details.isEmpty())
        assertFalse(details[0].isEmpty())
        mayValid(details[0].address)
        mayValid(details[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val list = listOf("0x2eAb08daA285183F9A04269747D4125F08e634B0", "0x36f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        val details = api.address.details(list)
        assertNotNull(details)
        assertTrue(details.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val list = listOf("0x1eAb08daA285183F9A04269747D4125F08e634B0", "0x6f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        api.address.details(list)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.address.details(emptyList())
    }
}