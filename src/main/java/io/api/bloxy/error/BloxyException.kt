package io.api.bloxy.error


/**
 * Bloxy Server Error
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
open class BloxyException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}