package io.api.bloxy.core.impl

import com.beust.klaxon.Klaxon
import io.api.bloxy.error.BloxyException
import io.api.bloxy.error.HttpException
import io.api.bloxy.error.ParamException
import io.api.bloxy.error.ParseException
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.BloxyError
import io.api.bloxy.util.KlaxonArgs
import io.api.bloxy.util.KlaxonConverters
import io.api.bloxy.util.ParamConverter
import org.jetbrains.annotations.NotNull


/**
 * Basic Bloxy API class with common functionality
 *
 * @param client http client
 * @param module API module
 * @param key API key
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
abstract class BasicProvider(private val client: IHttpClient, module: String, key: String) : ParamConverter() {

    private val base = "https://bloxy.info/api/$module/"
    private val keyParam = "&key=$key&format=structure"

    protected val converter = Klaxon().fieldConverter(KlaxonArgs::class, KlaxonConverters.argsConverter)

    /**
     * Perform GET HTTP request
     * @param urlParams URL params for request
     */
    protected fun getData(urlParams: String): String {
        try {
            return client.get(base + urlParams + keyParam)
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }

    /**
     * Perform GET HTTP request and parse result
     * @param urlParams URL params for request
     * @param skipErrors errors messages to skip from BloxyServer
     */
    @NotNull
    protected inline fun <reified T> get(urlParams: String, skipErrors: List<Regex> = emptyList()): List<T> {
        return parse(getData(urlParams), skipErrors)
    }

    /**
     * Parse json string to list of T
     * @param json to parse
     * @param skipErrors errors messages to skip from BloxyServer
     */
    @NotNull
    protected inline fun <reified T> parse(json: String, skipErrors: List<Regex> = emptyList()): List<T> {
        return try {
            if (json.isBlank()) emptyList() else converter.parseArray(json) ?: emptyList()
        } catch (e: Exception) {
            try {
                val bloxyError = converter.parse<BloxyError>(json) ?: throw ParseException(e.message, e.cause)
                if (skipErrors.stream().anyMatch { er -> er.containsMatchIn(bloxyError.error) })
                    emptyList()
                else
                    throw BloxyException(bloxyError.error)
            } catch (e: Exception) {
                when (e) {
                    is BloxyException -> throw e
                    else -> throw ParseException(e.message, e.cause)
                }
            }
        }
    }

    /**
     * Is used as GET fun for API endpoints with limit and offset
     * Could cycle to get max amount and emulate single request
     *
     * Example (max limit 100, max offset 100000) with limit set to 20000
     * Method will return all 20000 entries as single request
     *
     * @param params URL params for request
     * @param limit user specified for API call
     * @param offset user specified for API call
     * @param maxLimit for API call
     * @param maxOffset for API call
     * @param skipErrors errors messages to skip from BloxyServer
     */
    @NotNull
    protected inline fun <reified T> getOffset(
        params: String,
        limit: Int,
        offset: Int,
        maxLimit: Int = 100000,
        maxOffset: Int = 100000,
        skipErrors: List<Regex> = emptyList()
    ): List<T> {
        if (limit < 1) return emptyList()

        try {
            val result: MutableList<T> = ArrayList()
            var temp: List<T>
            val realLimit = toLimit(limit, maxLimit + maxOffset)
            var cycleLimit = toLimit(limit, maxLimit)
            var cycleOffset = toOffset(offset, maxOffset)
            do {
                temp = get("$params&limit=$cycleLimit&offset=$cycleOffset", skipErrors)
                result.addAll(temp)

                val step = cycleLimit + cycleOffset
                cycleLimit = realLimit - step
                cycleOffset += step
            } while (cycleLimit > 0 && temp.isNotEmpty())

            return result
        } catch (e: Exception) {
            when (e) {
                is BloxyException, is HttpException, is ParseException, is ParamException -> throw e
                else -> throw HttpException(e.message, e.cause)
            }
        }
    }
}