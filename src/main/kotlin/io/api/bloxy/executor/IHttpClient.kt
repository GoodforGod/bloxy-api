package io.api.bloxy.executor


/**
 * Http Client with HTTP GET method only
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface IHttpClient {

    /**
     * HTTP GET request
     * @param url as string
     * @return response body as string
     */
    fun get(url: String): String
}