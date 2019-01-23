package io.api.bloxy.core.address

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.01.2019
 */
class AnnotationsTests : BloxyTester() {

    @Test
    fun valid() {
        val words = listOf("UserWallet")
        val map = api.address.annotations(words)
        assertNotNull(map)
        assertFalse(map.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val words = listOf("eAb08daA285183F9A04269747D4125F08e634B0")
        val map = api.address.annotations(words)
        assertNotNull(map)
        assertTrue(map.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param`() {
        val words = emptyList<String>()
        api.address.annotations(words)
    }
}