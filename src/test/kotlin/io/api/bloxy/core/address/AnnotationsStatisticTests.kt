package io.api.bloxy.core.address

import io.api.bloxy.core.BloxyTester
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.01.2019
 */
class AnnotationsStatisticTests : BloxyTester() {

    @Test
    fun valid() {
        val map = api.address.annotationStatistic()
        assertNotNull(map)
        assertFalse(map.isEmpty())
    }
}
