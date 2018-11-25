package io.api.bloxy.util

import org.junit.Test
import kotlin.test.assertEquals


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class ParamConverterTests : ParamConverter() {

    @Test
    fun `to zero less zero`() {
        assertEquals(0, toZero(-100))
    }

    @Test
    fun `to non zero less zero`() {
        assertEquals(1, toNoZero(-10))
        assertEquals(1.0e-6, toNoZero(-10.0))
    }

    @Test
    fun `to limit less min`() {
        assertEquals(1, toLimit(-1000))
    }

    @Test
    fun `to limit more max`() {
        assertEquals(1000, toLimit(1001, 1000))
    }

    @Test
    fun `to limit in range`() {
        assertEquals(1000, toLimit(1000))
    }

    @Test
    fun `to depth less min`() {
        assertEquals(1, toDepth(-1000))
    }

    @Test
    fun `to depth more max`() {
        assertEquals(1000, toDepth(1001, 1000))
    }

    @Test
    fun `to depth in range`() {
        assertEquals(300, toDepth(1000))
    }

    @Test
    fun `to offset less min`() {
        assertEquals(0, toOffset(-1000))
    }

    @Test
    fun `to offset more max`() {
        assertEquals(1000, toOffset(1001, 1000))
    }

    @Test
    fun `to offset in range`() {
        assertEquals(1000, toOffset(1000))
    }
}