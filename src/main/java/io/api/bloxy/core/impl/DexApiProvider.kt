package io.api.bloxy.core.impl

import io.api.bloxy.core.IDexApi
import io.api.bloxy.executor.IHttpClient


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class DexApiProvider(client: IHttpClient, key:String) : IDexApi, BasicProvider(client, "", key){
}