package io.api.bloxy.core.impl

import io.api.bloxy.error.BloxyException
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.executor.impl.HttpClient
import java.util.function.Supplier


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
class BloxyApi(key: String, supplier: Supplier<IHttpClient> = Supplier { HttpClient() }) {

    private val txApi: TransactionApiProvider
    private val dexApi: DexApiProvider
    private val tokenApi: TokenApiProvider
    private val addressApi: AddressApiProvider
    private val moneyFlowApi: MoneyFlowApiProvider
    private val tokenSaleApi: TokenSaleApiProvider

    init {
        if (key.isEmpty()) throw BloxyException("API key can not be null or empty")

        this.txApi = TransactionApiProvider(supplier.get(), key)
        this.dexApi = DexApiProvider(supplier.get(), key)
        this.tokenApi = TokenApiProvider(supplier.get(), key)
        this.addressApi = AddressApiProvider(supplier.get(), key)
        this.moneyFlowApi = MoneyFlowApiProvider(supplier.get(), key)
        this.tokenSaleApi = TokenSaleApiProvider(supplier.get(), key)
    }

    fun address(): AddressApiProvider = addressApi

    fun token(): TokenApiProvider = tokenApi

    fun dex(): DexApiProvider = dexApi

    fun tokenSale(): TokenSaleApiProvider = tokenSaleApi

    fun moneyFlow(): MoneyFlowApiProvider = moneyFlowApi

    fun tx(): TransactionApiProvider = txApi
}