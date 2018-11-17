package io.api.bloxy.core.impl

import io.api.bloxy.core.*
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

    private val txApi: ITransactionApi
    private val dexApi: IDexApi
    private val tokenApi: ITokenApi
    private val addressApi: IAddressApi
    private val moneyFlowApi: IMoneyFlowApi
    private val tokenSaleApi: ITokenSaleApi

    init {
        this.txApi = TransactionApiProvider(supplier.get(), key)
        this.dexApi = DexApiProvider(supplier.get(), key)
        this.tokenApi = TokenApiProvider(supplier.get(), key)
        this.addressApi = AddressApiProvider(supplier.get(), key)
        this.moneyFlowApi = MoneyFlowApiProvider(supplier.get(), key)
        this.tokenSaleApi = TokenSaleApiProvider(supplier.get(), key)
    }

    fun address(): IAddressApi = addressApi

    fun token(): ITokenApi = tokenApi

    fun dex(): IDexApi = dexApi

    fun tokenSale(): ITokenSaleApi = tokenSaleApi

    fun moneyFlow(): IMoneyFlowApi = moneyFlowApi

    fun tx(): ITransactionApi = txApi
}