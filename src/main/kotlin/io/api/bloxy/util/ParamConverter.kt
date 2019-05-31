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
        val MIN_DATE: LocalDate = LocalDate.of(2010, 1, 1)
        val MAX_DATE: LocalDate = LocalDate.of(2080, 1, 1)

        val MIN_DATETIME: LocalDateTime = LocalDateTime.of(2010, 1, 1, 1, 1, 1, 1)
        val MAX_DATETIME: LocalDateTime = LocalDateTime.of(2080, 1, 1, 1, 1, 1, 1)

        fun String.asDateTime() : LocalDateTime? {
            return try {
                LocalDateTime.parse(this, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            } catch (e: Exception) {
                return null
            }
        }

        fun String.asDate() : LocalDate? {
            return try {
                LocalDate.parse(this)
            } catch (e: Exception) {
                return null
            }
        }
    }

    private fun toDate(date: LocalDate): LocalDate {
        return when {
            date.isBefore(MIN_DATE) -> MIN_DATE
            date.isAfter(MAX_DATE) -> MAX_DATE
            else -> date
        }
    }

    private fun toDateTime(dateTime: LocalDateTime): LocalDateTime {
        return when {
            dateTime.isBefore(MIN_DATETIME) -> MIN_DATETIME
            dateTime.isAfter(MAX_DATETIME) -> MAX_DATETIME
            else -> dateTime
        }
    }

    fun toZero(value: Int) = if (value < 0) 0 else value

    fun toZero(value: Double) = if (value < 0) .0 else value

    fun toNoZero(value: Int) = if (value < 1) 1 else value

    fun toNoZero(value: Double) = if (value < 0) 1.0e-6 else value

    fun toLimit(limit: Int, max: Int = 100000): Int = if (limit > max) max else if (limit < 1) 1 else limit

    fun toDepth(depth: Int, max: Int = 300): Int = if (depth > max) max else if (depth < 1) 1 else depth

    fun toOffset(offset: Int, max: Int = 100000): Int = if (offset > max) max else if (offset < 0) 0 else offset

    fun toDays(days: Int, maxDays: Int = 1000): Int = if (days > maxDays) maxDays else if (days < 1) 1 else days

    fun asIgnored(ignoreAmount: Int, default: Int = 2000, max: Int = 10000): String {
        return when {
            ignoreAmount == default -> ""
            ignoreAmount < 100 -> "&ignore_addresses_with_transaction_limit=100"
            ignoreAmount > max -> "&ignore_addresses_with_transaction_limit=$max"
            else -> "&ignore_addresses_with_transaction_limit=$ignoreAmount"
        }
    }

    fun asParam(value: String, prefix: String, delim: String): String {
        return if (value.isEmpty()) "" else prefix + value + delim
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
        return asParam(checkAddrRequired(contracts), "token[]=", "&token[]=")
    }

    fun tokenAsParam(contracts: List<String>, prefix: String = ""): String {
        return asParam(checkAddr(contracts), "${prefix}token[]=", "&token[]=")
    }

    fun addressAsParamRequired(addresses: List<String>): String {
        return asParam(checkAddrRequired(addresses), "address[]=", "&address[]=")
    }

    fun addressAsParam(addresses: List<String>, prefix: String = ""): String {
        return asParam(checkAddr(addresses), "${prefix}address[]=", "&address[]=")
    }

    fun addressAsParamRequired(address: String, prefix: String = ""): String {
        return asParam(address, "${prefix}address[]=", "&address[]=")
    }
}