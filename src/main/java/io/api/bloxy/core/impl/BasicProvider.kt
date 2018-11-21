package io.api.bloxy.core.impl

import com.beust.klaxon.Klaxon
import io.api.bloxy.error.HttpException
import io.api.bloxy.error.ParseException
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.manager.ParamConverter


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
abstract class BasicProvider(private val client: IHttpClient, module: String, key: String) : ParamConverter() {

    private val base = "https://bloxy.info/api/$module/"
    private val keyParam = "&key=$key&format=structure"

    protected val converter = Klaxon()

    protected fun get(urlParams: String): String {
        try {
            return client.get(base + urlParams + keyParam)
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }

    protected inline fun <reified T> parse(json: String): List<T> {
        try {
            return if (json.isEmpty()) emptyList() else converter.parseArray(json) ?: emptyList()
        } catch (e: Exception) {
            throw ParseException(e.message, e.cause)
        }
    }

    protected inline fun <reified T> getOffset(
        params: String,
        limit: Int,
        offset: Int,
        maxLimit: Int = 100000,
        maxOffset: Int = 100000
    ): List<T> {
        if (limit < 1) return emptyList()

        try {
            val result: MutableList<T> = ArrayList()
            var temp: List<T>
            val realLimit = toLimit(limit, maxLimit + maxOffset)
            var cycleLimit = toLimit(limit, maxLimit)
            var usedOffset = toOffset(offset, maxOffset)
            do {
                temp = parse(get("$params&limit=$cycleLimit&offset=$usedOffset"))
                result.addAll(temp)

                val step = cycleLimit + usedOffset
                cycleLimit = realLimit - step
                usedOffset += step
            } while (cycleLimit >= 0 && temp.isNotEmpty())

            return result
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }
}