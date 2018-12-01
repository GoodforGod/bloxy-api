package io.api.bloxy.core.dex

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class ProtocolsTests : BloxyTester() {

    @Test
    fun valid() {
        val result = api.dex.protocols()
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].protocol)
        assertNotNull(result[0].smartContracts)
        assertNotNull(result[0].toString())
    }
}