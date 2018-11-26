package io.api.bloxy.error


/**
 * Http Client Related Error
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
class HttpException(message: String?, cause: Throwable?) : BloxyException(message, cause)