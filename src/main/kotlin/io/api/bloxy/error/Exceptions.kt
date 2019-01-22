package io.api.bloxy.error


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 22.01.2019
 */
open class BloxyException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}

class ParamException(message: String?) : BloxyException(message)
class SubscriptionException(message: String?) : BloxyException(message)

class HttpException(message: String?, cause: Throwable?) : BloxyException(message, cause)
class ParseException(message: String?, cause: Throwable?) : BloxyException(message, cause)
