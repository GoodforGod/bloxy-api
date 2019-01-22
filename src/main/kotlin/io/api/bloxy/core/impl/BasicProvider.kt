package io.api.bloxy.core.impl

import com.beust.klaxon.Klaxon
import io.api.bloxy.error.BloxyException
import io.api.bloxy.error.HttpException
import io.api.bloxy.error.ParseException
import io.api.bloxy.error.SubscriptionException
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
     *
     * @throws io.api.bloxy.error.HttpException in case http connection errors
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
     *
     * @throws io.api.bloxy.error.ParamException in case of invalid input params
     */
    @NotNull
    protected inline fun <reified T> get(urlParams: String, skipErrors: List<Regex> = emptyList()): List<T> {
        return parse(getData(urlParams), skipErrors)
    }

    /**
     * Parse json string to list of T
     * @param json to parse
     * @param skipErrors errors messages to skip from BloxyServer
     *
     * @throws io.api.bloxy.error.BloxyException (PARENT exception class) in case of Bloxy service errors
     * @throws io.api.bloxy.error.ParamException in case of invalid input params
     */
    @NotNull
    protected inline fun <reified T> parse(json: String, skipErrors: List<Regex> = emptyList()): List<T> {
        return try {
            if (json.isBlank()) emptyList() else converter.parseArray(json) ?: emptyList()
        } catch (e: Exception) {
            try {
                val bloxyError = converter.parse<BloxyError>(json) ?: throw ParseException(e.message, e.cause)
                when {
                    skipErrors.stream().anyMatch { er -> er.containsMatchIn(bloxyError.error) } -> emptyList()
                    bloxyError.error.startsWith("Your subscription is") -> throw SubscriptionException(bloxyError.error)
                    else -> throw BloxyException(bloxyError.error)
                }
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
     * Example (max limit 100, max offset 100000)
     * Call with limit 20000, method will perform 200 requests and return all 20000 entries
     * Like it was a single request
     *
     * @param params URL params for request
     * @param limit user specified for API call
     * @param offset user specified for API call
     * @param maxLimit for API call
     * @param maxOffset for API call
     * @param skipErrors errors messages to skip from BloxyServer
     *
     * @throws io.api.bloxy.error.BloxyException (PARENT exception class) in case of Bloxy service errors
     * @throws io.api.bloxy.error.ParseException in case of response parse exception
     * @throws io.api.bloxy.error.HttpException in case http connection errors
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

            val fullLimit = toLimit(limit, maxLimit + maxOffset)
            var resultLeft = fullLimit
            var cycleOffset = toOffset(offset, maxOffset)
            var cycleLimit = if (fullLimit > maxLimit) toLimit(limit, maxLimit - cycleOffset) else toLimit(limit, maxLimit)
            do {
                temp = get("$params&limit=$cycleLimit&offset=$cycleOffset", skipErrors)
                result.addAll(temp)

                resultLeft -= cycleLimit
                cycleOffset = toOffset(cycleLimit + cycleOffset, maxOffset)
                cycleLimit = toLimit(resultLeft, maxLimit)
            } while (resultLeft > 0 && temp.isNotEmpty() && temp.size == cycleLimit)

            return result
        } catch (e: Exception) {
            when (e) {
                is BloxyException, is HttpException, is ParseException -> throw e
                else -> throw HttpException(e.message, e.cause)
            }
        }
    }
}