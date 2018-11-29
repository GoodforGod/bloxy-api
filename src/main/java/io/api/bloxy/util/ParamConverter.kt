package io.api.bloxy.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors


/**
 * Functions for API provider to convert params to valid states
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class ParamConverter : ParamValidator() {

    companion object {
        val MIN_DATE: LocalDate = LocalDate.of(2001, 1, 1)
        val MAX_DATE: LocalDate = LocalDate.of(2098, 1, 1)

        val MIN_DATETIME: LocalDateTime = LocalDateTime.of(2001, 1, 1, 1, 1, 1, 1)
        val MAX_DATETIME: LocalDateTime = LocalDateTime.of(2098, 1, 1, 1, 1, 1, 1)

        fun parseDateTime(value: String) : LocalDateTime? {
            return try {
                LocalDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            } catch (e: Exception) {
                return null
            }
        }

        fun parseDate(value: String) : LocalDate? {
            return try {
                LocalDate.parse(value)
            } catch (e: Exception) {
                return null
            }
        }
    }

    private fun toDate(value: LocalDate): LocalDate {
        if (value.isBefore(MIN_DATE))
            return MIN_DATE

        return if (value.isAfter(MAX_DATE)) MAX_DATE else value
    }

    private fun toDateTime(value: LocalDateTime): LocalDateTime {
        if (value.isBefore(MIN_DATETIME))
            return MIN_DATETIME

        return if (value.isAfter(MAX_DATETIME)) MAX_DATETIME else value
    }

    fun toZero(value: Int) = if (value < 0) 0 else value

    fun toNoZero(value: Int) = if (value < 1) 1 else value

    fun toNoZero(value: Double) = if (value < 0) 1.0e-6 else value

    fun toLimit(limit: Int, max: Int = 100000): Int = if (limit > max) max else if (limit < 1) 1 else limit

    fun toDepth(depth: Int, max: Int = 300): Int = if (depth > max) max else if (depth < 1) 1 else depth

    fun toOffset(offset: Int, max: Int = 100000): Int = if (offset > max) max else if (offset < 0) 0 else offset

    fun toTimeSpan(timeSpan: Int, maxDays: Int = 1000): Int {
        return if (timeSpan > maxDays) maxDays else if (timeSpan < 1) 1 else timeSpan
    }

    fun toIgnored(ignoreAmount: Int, max: Int = 10000): Int {
        return if (ignoreAmount < 100) 100 else if (ignoreAmount > max) max else ignoreAmount
    }

    fun asIgnored(ignoreAmount: Int, default: Int = 2000, max: Int = 10000): String {
        return when {
            ignoreAmount == default -> ""
            ignoreAmount < 100 -> "&ignore_addresses_with_transaction_limit=100"
            ignoreAmount > max -> "&ignore_addresses_with_transaction_limit=$max"
            else -> "&ignore_addresses_with_transaction_limit=$ignoreAmount"
        }
    }

    fun asParam(values: List<String>, prefix: String, delim: String): String {
        return if (values.isEmpty()) "" else values.stream().collect(Collectors.joining(delim, prefix, ""))
    }

    fun dateAsParam(paramName: String, date: LocalDateTime): String {
        return if (date == MIN_DATETIME || date == MAX_DATETIME) "" else "&$paramName=${toDateTime(date)}"
    }

    fun dateAsParam(paramName: String, date: LocalDate): String {
        return if (date == MIN_DATE || date == MAX_DATE) "" else "&$paramName=${toDate(date)}"
    }

    fun tokenAsParamRequired(contracts: List<String>): String {
        return asParam(checkAddressRequired(contracts), "token[]=", "&token[]=")
    }

    fun tokenAsParam(contracts: List<String>, prefix: String = ""): String {
        return asParam(checkAddress(contracts), "${prefix}token[]=", "&token[]=")
    }

    fun addressAsParamRequired(addresses: List<String>): String {
        return asParam(checkAddressRequired(addresses), "address[]=", "&address[]=")
    }

    fun addressAsParam(addresses: List<String>, prefix: String = ""): String {
        return asParam(checkAddress(addresses), "${prefix}address[]=", "&address[]=")
    }
}