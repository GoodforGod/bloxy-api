package io.api.bloxy.core.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.api.bloxy.error.ResponseException
import io.api.bloxy.executor.IHttpClient
import java.util.stream.Collectors


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
abstract class BasicProvider(
    private val client: IHttpClient,
    module: String,
    key: String
) {

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
    private val converter = Gson()

    private val base = "https://bloxy.info/api/$module/"
    private val keyParam = "&key=$key&format=structure"

    fun <T> get(urlParams: String, type: Class<T>): List<T> {
        val typeToken = object : TypeToken<List<T>>() {}.type
        val response = client.get(base + urlParams + keyParam)
        return if (response.isNotEmpty()) converter.fromJson(response) else throw ResponseException("Empty response from server")
    }

    fun addressAsParam(addresses: List<String>): String {
        return addresses.stream().collect(Collectors.joining("&address[]=", "address[]=", ""))
    }
}