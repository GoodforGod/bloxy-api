package io.api.bloxy.core.impl

import com.beust.klaxon.Klaxon
import io.api.bloxy.error.HttpException
import io.api.bloxy.error.ParseException
import io.api.bloxy.executor.IHttpClient
import java.time.LocalDate
import java.time.LocalDateTime
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

    protected inline fun <reified T> parse(json: String): List<T> {
        try {
            return if (json.isEmpty()) emptyList() else converter.parseArray(json) ?: emptyList()
        } catch (e: Exception) {
            throw ParseException(e.message, e.cause)
        }
    }

    protected inline fun <reified T> getOffset(
        urlParams: String,
        limit: Int,
        offset: Int,
        maxLimit: Int = 100000,
        maxOffset: Int = 100000
    ): List<T> {
        if (limit < 1)
            return emptyList()

        try {
            val result: MutableList<T> = ArrayList()
            var temp: List<T>
            val realLimit = toLimit(limit, maxLimit + maxOffset)
            var cycleLimit = toLimit(limit, maxLimit)
            var usedOffset = toOffset(offset, maxOffset)
            do {
                temp = parse(get("$urlParams&limit=$cycleLimit&offset=$usedOffset"))
                result.addAll(temp)

                val step = cycleLimit + usedOffset
                cycleLimit = realLimit - step
                usedOffset += step
            } while (cycleLimit > 0 && temp.isNotEmpty())

            return result
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }

    fun toNoZero(value: Int) = if (value < 1) 1 else value

    fun toNoZero(value: Double) = if (value < 0) 0.001 else value

    fun toLimit(limit: Int, max: Int = 100000): Int = if (limit > max) max else if (limit < 1) 1 else limit

    fun toDepth(depth: Int, max: Int = 300): Int = if (depth > max) max else if (depth < 1) 1 else depth

    fun toOffset(offset: Int, max: Int = 100000): Int = if (offset > max) max else if (offset < 0) 0 else offset

    fun toTimeSpan(timeSpan: Int, maxDays: Int = 1000): Int {
        return if (timeSpan > maxDays) maxDays else if (timeSpan < 1) 1 else timeSpan
    }

    fun toIgnored(ignoreAmount: Int, max: Int = 10000): Int {
        return if (ignoreAmount < 100) 100 else if (ignoreAmount > max) max else ignoreAmount
    }

    fun asParam(values: List<String>, prefix: String, delim: String): String {
        return values.stream().collect(Collectors.joining(delim, prefix, ""))
    }

    fun dateTimeAsParam(paramName: String, date: LocalDateTime): String {
        return if (date == LocalDateTime.MIN || date == LocalDateTime.MAX) "" else "&$paramName=$date"
    }

    fun dateAsParam(paramName: String, date: LocalDate): String {
        return if (date == LocalDate.MIN || date == LocalDate.MAX) "" else "&$paramName=$date"
    }

    fun tokenAsParam(addresses: List<String>): String {
        return asParam(addresses, "token[]=", "&token[]=")
    }

    fun addressAsParam(addresses: List<String>): String {
        return asParam(addresses, "address[]=", "&address[]=")
    }
}