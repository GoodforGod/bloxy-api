package io.api.bloxy.core

import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.tokensale.*
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface ITokenSaleApi {

    fun sales(
        contracts: List<String> = emptyList(),
        limit: Int = 30,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<Sale>

    fun saleTxs(
        contracts: List<String> = emptyList(),
        limit: Int = 30,
        offset: Int = 0,
        timeSpanDays: Int = 30
    ): List<SaleTx>

    fun dailyStats(
        contract: String
    ): List<SaleDaily>

    fun addrStats(
        contract: String
    ): List<SaleAddrStatistic>

    fun buyers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0
    ): List<SaleBuyer>

    fun wallets(
        contract: String,
        withIntermediary: Boolean = false
    ): List<SaleWallet>

    fun moneyDistribution(
        contract: String,
        depth: Int = 10,
        limit: Int = 100,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = LocalDateTime.MIN,
        till: LocalDateTime = LocalDateTime.MAX,
        snapshot: LocalDateTime = LocalDateTime.MIN
    ): List<Address>

    fun txsDistribution(
        contract: String,
        depth: Int = 10,
        limit: Int = 5000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = LocalDateTime.MIN,
        till: LocalDateTime = LocalDateTime.MAX,
        snapshot: LocalDateTime = LocalDateTime.MIN
    ): List<Tx>

    fun moneySources(
        contract: String,
        depth: Int = 5,
        limit: Int = 100,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = LocalDateTime.MIN,
        till: LocalDateTime = LocalDateTime.MAX,
        snapshot: LocalDateTime = LocalDateTime.MIN
    ): List<Address>

    fun txsSources(
        contract: String,
        depth: Int = 5,
        limit: Int = 5000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = LocalDateTime.MIN,
        till: LocalDateTime = LocalDateTime.MAX,
        snapshot: LocalDateTime = LocalDateTime.MIN
    ): List<Tx>

    fun tokenDistribution(
        contract: String,
        limit: Int = 5000,
        offset: Int = 0,
        since: LocalDateTime = LocalDateTime.MIN,
        till: LocalDateTime = LocalDateTime.MAX
    ): List<Tx>
}