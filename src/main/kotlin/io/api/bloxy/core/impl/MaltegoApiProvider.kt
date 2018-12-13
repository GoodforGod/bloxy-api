package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.12.2018
 */
class MaltegoApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "dex", key) {

}