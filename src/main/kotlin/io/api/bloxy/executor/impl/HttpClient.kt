package io.api.bloxy.executor.impl

import io.api.bloxy.executor.IHttpClient
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors
import java.util.zip.GZIPInputStream
import java.util.zip.InflaterInputStream


/**
 * @see IHttpClient
 *
 * Supports GZIP and deflate decoding
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
            "Accept-Encoding" to "deflate, gzip",
            "Accept-Charset" to "UTF-8",
            "User-Agent" to "Chrome/68.0.3440.102",
            "Content-Type" to "application/x-www-form-urlencoded"
        )
    }

    private fun HttpURLConnection.getReader(): InputStreamReader {
        val stream: InputStream = if (responseCode >= 400) errorStream else inputStream

        return when (contentEncoding) {
            "deflate" -> InputStreamReader(InflaterInputStream(stream), "UTF-8")
            "gzip" -> InputStreamReader(GZIPInputStream(stream), "UTF-8")
            else -> InputStreamReader(stream, "UTF-8")
        }
    }

    override fun get(url: String): String {
        (URL(url).openConnection().apply {
            readTimeout = if (this@HttpClient.readTimeout < 0) 0 else this@HttpClient.readTimeout
            connectTimeout = if (this@HttpClient.connectTimeout < 1) 1 else this@HttpClient.connectTimeout
            headers.forEach { e -> setRequestProperty(e.key, e.value) }
            getHeaderField("Location")?.let { return get(it) }
        } as HttpURLConnection).getReader().use {
            return BufferedReader(it).lines().collect(Collectors.joining())
        }
    }
}