package io.api.bloxy.core.impl

import io.api.bloxy.core.ITokenSaleApi
import io.api.bloxy.executor.IHttpClient


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TokenSaleApiProvider(client: IHttpClient, key:String) : ITokenSaleApi, BasicProvider(client, "", key) {
}