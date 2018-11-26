package io.api.bloxy.core.dex

import io.api.bloxy.core.Tester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
class ProtocolsTests : Tester() {

    @Test
    fun valid() {
        val details = api.dex.protocols()
        assertNotNull(details)
        assertFalse(details.isEmpty())
        assertFalse(details[0].isEmpty())
    }
}