package io.api.bloxy.core

import io.api.bloxy.core.impl.BloxyApi
import org.junit.Assert
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class Tester : Assert() {

    val apiKey by lazy { System.getenv("BLOXY_API") ?: "" }
    val api by lazy { BloxyApi(apiKey) }

    @Test
    fun isApiKeyValid() {
        assertFalse(apiKey.isBlank())
        assertNotNull(api)
    }
}