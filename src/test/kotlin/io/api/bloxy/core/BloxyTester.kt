package io.api.bloxy.core

import io.api.bloxy.core.impl.BloxyApi
import org.junit.Assert


/**
 * Test extension class with bloxy api initialized by api key
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class BloxyTester : Assert() {
    val key by lazy { System.getenv("BLOXY_API").ifEmpty { throw NullPointerException("API KEY IS NULL") } }
    val api by lazy { BloxyApi(key) }
}