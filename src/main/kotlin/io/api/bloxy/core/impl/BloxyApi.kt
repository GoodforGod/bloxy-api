package io.api.bloxy.core.impl

import io.api.bloxy.error.ParamException
import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.executor.impl.HttpClient
import java.util.function.Supplier


/**
 * Bloxy Api Main Class
 *
 * @param key API key
 * @param supplier http client supplier
 *
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @throws ParamException in case of blank API key
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
class BloxyApi @JvmOverloads constructor(key: String, supplier: Supplier<IHttpClient> = Supplier { HttpClient() }) {

    val tx: TransactionApiProvider = TransactionApiProvider(supplier.get(), key)
    val dex: DexApiProvider = DexApiProvider(supplier.get(), key)
    val dapp: DAppApiProvider = DAppApiProvider(supplier.get(), key)
    val token: TokenApiProvider = TokenApiProvider(supplier.get(), key)
    val address: AddressApiProvider = AddressApiProvider(supplier.get(), key)
    val makerDao: MakerDaoApiProvider = MakerDaoApiProvider(supplier.get(), key)
    val contract: ContractApiProvider = ContractApiProvider(supplier.get(), key)
    val moneyFlow: MoneyFlowApiProvider = MoneyFlowApiProvider(supplier.get(), key)
    val tokenSale: TokenSaleApiProvider = TokenSaleApiProvider(supplier.get(), key)

    init {
        if (key.isBlank()) throw ParamException("API key can not be null or empty")
    }
}