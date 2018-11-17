package io.api.bloxy.core.impl

import io.api.bloxy.core.ITransactionApi
import io.api.bloxy.executor.IHttpClient


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TransactionApiProvider(client: IHttpClient, key:String) : ITransactionApi, BasicProvider(client, "", key) {
}