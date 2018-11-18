package io.api.bloxy.core

import io.api.bloxy.model.Address
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface IMoneyFlowApi {
    fun addressVolumes(
        addresses: List<String>,
               contract: String = "ETH",
               since: LocalDateTime = LocalDateTime.MIN,
               till: LocalDateTime = LocalDateTime.MAX)

    fun topSenders(
        address: String,
                   contract: String = "ETH",
                   limit: Int = 100,
                   since: LocalDateTime = LocalDateTime.MIN,
                   till: LocalDateTime = LocalDateTime.MAX)

    fun topReceives(
        address: String,
                   contract: String = "ETH",
                   limit: Int = 100,
                   since: LocalDateTime = LocalDateTime.MIN,
                   till: LocalDateTime = LocalDateTime.MAX)

    fun moneyDistribution(
        address: String,
        contract: String = "ETH",
        depth: Int = 10,
        limit: Int = 100,
        minTxAmount: Int = 0,
        minBalance: Number = .0,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = LocalDateTime.MIN,
        till: LocalDateTime = LocalDateTime.MAX,
        snapshot: LocalDateTime = LocalDateTime.MIN
    ) : List<Address>

    fun
}