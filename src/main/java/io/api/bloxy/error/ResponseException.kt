package io.api.bloxy.error


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
class ResponseException : RuntimeException {
    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable) : super(message, cause)
}