package io.api.bloxy.util

import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue


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

    @Test
    fun `ignored less`() {
        assertEquals(100, toIgnored(-1000))
    }

    @Test
    fun `ignored more`() {
        assertEquals(10000, toIgnored(20000))
    }

    @Test
    fun `ignored in range`() {
        assertEquals(1000, toIgnored(1000))
    }

    @Test
    fun `as ignored less`() {
        assertTrue { asIgnored(-1000).contains("limit=100") }
    }

    @Test
    fun `as ignored more`() {
        assertTrue { asIgnored(100000).contains("limit=10000") }
    }

    @Test
    fun `as ignored in range`() {
        assertTrue { asIgnored(1000).contains("limit=1000") }
    }

    @Test
    fun `date parse null result`() {
        assertNull(ParamConverter.parseDate("12411"))
    }

    @Test
    fun `to date less`() {
        assertTrue { dateAsParam("date", LocalDate.of(1920, 1, 1)).contains(ParamConverter.MIN_DATE.toString()) }
    }

    @Test
    fun `to date more`() {
        assertTrue { dateAsParam("date", LocalDate.of(2120, 1, 1)).contains(ParamConverter.MAX_DATE.toString()) }
    }

    @Test
    fun `to date in range`() {
        assertTrue { dateAsParam("date", LocalDate.of(2020, 1, 1)).contains("2020-01-01") }
    }

    @Test
    fun `to time less`() {
        assertTrue {
            dateAsParam("date", LocalDateTime.of(LocalDate.of(1920, 1, 1), LocalTime.now()))
                .contains(ParamConverter.MIN_DATE.toString())
        }
    }

    @Test
    fun `to time more`() {
        assertTrue {
            dateAsParam("date", LocalDateTime.of(LocalDate.of(2120, 1, 1), LocalTime.now()))
                .contains(ParamConverter.MAX_DATE.toString())
        }
    }

    @Test
    fun `to time in range`() {
        assertTrue {
            dateAsParam("date", LocalDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.now()))
                .contains("2020-01-01")
        }
    }
}