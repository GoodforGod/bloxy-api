package io.api.bloxy.core.impl

import io.api.bloxy.core.IMoneyFlowApi
import io.api.bloxy.executor.IHttpClient


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class MoneyFlowApiProvider(client: IHttpClient, key:String) : IMoneyFlowApi, BasicProvider(client, "", key) {
}