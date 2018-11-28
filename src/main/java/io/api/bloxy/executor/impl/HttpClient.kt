package io.api.bloxy.executor.impl

import io.api.bloxy.error.HttpException
import io.api.bloxy.executor.IHttpClient
import java.net.URL
import java.util.stream.Collectors


/**
 * @see IHttpClient
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class HttpClient @JvmOverloads constructor(
    private val connectTimeout: Int = 8000,
    private val readTimeout: Int = 0
) : IHttpClient {

    companion object {
        private val headers: Map<String, String> = hashMapOf(
            "Accept-Language" to "en",
            "Accept-Encoding" to "deflate",
            "Accept-Charset" to "UTF-8",
            "User-Agent" to "Chrome/68.0.3440.105",
            "Content-Type" to "application/x-www-form-urlencoded"
        )
    }

    override fun get(url: String): String {
        try {
            URL(url).openConnection().apply {
                readTimeout = this@HttpClient.readTimeout
                connectTimeout = this@HttpClient.connectTimeout
                headers.forEach { e -> setRequestProperty(e.key, e.value) }
                getHeaderField("Location")?.let { return get(it) }
            }.getInputStream().use {
                return it.bufferedReader().lines().collect(Collectors.joining())
            }
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }
}