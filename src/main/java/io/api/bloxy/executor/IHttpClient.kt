package io.api.bloxy.executor


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface IHttpClient {
    fun get(url: String) : String
}