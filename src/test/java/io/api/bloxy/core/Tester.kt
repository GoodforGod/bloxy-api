package io.api.bloxy.core

import io.api.bloxy.core.impl.BloxyApi
import org.junit.Assert


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class Tester : Assert() {
    val apiKey by lazy { System.getenv("BLOXY_API").ifEmpty { throw NullPointerException("API KEY IS NULL") } }
    val api by lazy { BloxyApi(apiKey) }
}