package io.api.bloxy.executor


/**
 * Http Client
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface IHttpClient {
    fun get(url: String): String
}