package io.api.bloxy.core.address

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class DetailsTests : Tester() {

    @Test
    fun valid() {
        val list = listOf("0x9eAb08daA285183F9A04269747D4125F08e634B0", "0x16f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
        val details = api.address.details(list)
        assertNotNull(details)
        assertFalse(details.isEmpty())
        assertFalse(details[0].isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val list = listOf("0x1eAb08daA285183F9A04269747D4125F08e634B0", "0x26f05a632bBe3B4Ed51E3726b093FD33Aa55C6Df")
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