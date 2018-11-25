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

    init {
        Klaxon().fieldConverter(KlaxonArgs::class, KlaxonConverters.argsConverter)
    }

    protected fun getData(urlParams: String): String {
        try {
            return client.get(base + urlParams + keyParam)
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }

    protected inline fun <reified T> get(urlParams: String, skipErrors: List<Regex> = emptyList()): List<T> {
        return parse(getData(urlParams), skipErrors)
    }

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
            var usedOffset = toOffset(offset, maxOffset)
            do {
                temp = get("$params&limit=$cycleLimit&offset=$usedOffset", skipErrors)
                result.addAll(temp)

                val step = cycleLimit + usedOffset
                cycleLimit = realLimit - step
                usedOffset += step
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