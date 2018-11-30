package io.api.bloxy.core.impl

import io.api.bloxy.error.BloxyException
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.executor.impl.HttpClient
import java.util.function.Supplier


/**
 * Bloxy Api Main Class
 *
 * @param key API key
 * @param supplier http client supplier
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
class BloxyApi @JvmOverloads constructor(key: String, supplier: Supplier<IHttpClient> = Supplier { HttpClient() }) {

    val tx: TransactionApiProvider = TransactionApiProvider(supplier.get(), key)
    val dex: DexApiProvider = DexApiProvider(supplier.get(), key)
    val token: TokenApiProvider = TokenApiProvider(supplier.get(), key)
    val address: AddressApiProvider = AddressApiProvider(supplier.get(), key)
    val moneyFlow: MoneyFlowApiProvider = MoneyFlowApiProvider(supplier.get(), key)
    val tokenSale: TokenSaleApiProvider = TokenSaleApiProvider(supplier.get(), key)

    init {
        if (key.isBlank()) throw BloxyException("API key can not be null or empty")
    }
}