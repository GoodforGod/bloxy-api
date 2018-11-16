package io.api.bloxy.executor.impl

import io.api.bloxy.executor.IHttpExecutor
import java.net.URL
import java.util.stream.Collectors


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class HttpExecutor(
    private val connectTimeout: Int = 8000,
    private val readTimeout: Int = 5000
) : IHttpExecutor {

    companion object {
        val headers: Map<String, String> = hashMapOf(
            "" to "",
            "" to ""
        )
    }

    // TO DO ABOUT
    override fun get(url: String): String {
        URL(url).openConnection().apply {
            readTimeout = this@HttpExecutor.readTimeout
            connectTimeout = this@HttpExecutor.connectTimeout
            headers.forEach{e -> setRequestProperty(e.key, e.value)}
            if(getHeaderField("Location").isNullOrEmpty())
                return get(getHeaderField("Location"))
        }.getInputStream().use {
            return it.bufferedReader().lines().collect(Collectors.joining())
        }
    }
}