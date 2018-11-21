package io.api.bloxy.manager

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.stream.Collectors


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class ParamConverter : ParamValidator() {

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

    fun tokenAsParam(contracts: List<String>): String {
        return asParam(checkAddress(contracts), "token[]=", "&token[]=")
    }

    fun addressAsParam(addresses: List<String>): String {
        return asParam(checkAddress(addresses), "address[]=", "&address[]=")
    }
}