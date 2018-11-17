package io.api.bloxy.core.impl

import com.beust.klaxon.Klaxon
import io.api.bloxy.error.HttpException
import io.api.bloxy.error.ParseException
import io.api.bloxy.executor.IHttpClient
import java.util.stream.Collectors


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
abstract class BasicProvider(private val client: IHttpClient, module: String, key: String) {

    val converter = Klaxon()

    private val base = "https://bloxy.info/api/$module/"
    private val keyParam = "&key=$key&format=structure"

    protected fun get(urlParams: String): String {
        try {
            return client.get(base + urlParams + keyParam)
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }

    inline fun <reified T> parse(json: String): List<T> {
        try {
            return if (json.isEmpty()) emptyList() else converter.parseArray(json) ?: emptyList()
        } catch (e: Exception) {
            throw ParseException(e.message, e.cause)
        }
    }

    fun addressAsParam(addresses: List<String>): String {
        return addresses.stream().collect(Collectors.joining("&address[]=", "address[]=", ""))
    }

    fun tokenAsParam(addresses: List<String>): String {
        return addresses.stream().collect(Collectors.joining("&token[]=", "token[]=", ""))
    }
}