package io.api.bloxy.core

import io.api.bloxy.manager.ParamConverter.Companion.MAX_DATE
import io.api.bloxy.manager.ParamConverter.Companion.MAX_DATETIME
import io.api.bloxy.manager.ParamConverter.Companion.MIN_DATE
import io.api.bloxy.manager.ParamConverter.Companion.MIN_DATETIME
import io.api.bloxy.model.dto.Address
import io.api.bloxy.model.dto.Tx
import io.api.bloxy.model.dto.moneyflow.*
import java.time.LocalDate
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
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ) : List<Volume>

    fun topSenders(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ) : List<Sender>

    fun topReceivers(
        address: String,
        contract: String = "ETH",
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ) : List<Receiver>

    fun moneyDistribution(
        address: String,
        contract: String = "ETH",
        depth: Int = 10,
        limit: Int = 1000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Address>

    fun txsDistribution(
        address: String,
        contract: String = "ETH",
        depth: Int = 10,
        limit: Int = 5000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 2000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Tx>

    fun moneySource(
        address: String,
        contract: String = "ETH",
        depth: Int = 5,
        limit: Int = 1000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 1000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Address>

    fun txsSource(
        address: String,
        contract: String = "ETH",
        depth: Int = 5,
        limit: Int = 5000,
        offset: Int = 0,
        minTxAmount: Int = 0,
        minBalance: Double = .001,
        ignoreAddressWithTxs: Int = 1000,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME,
        snapshot: LocalDateTime = MIN_DATETIME
    ): List<Tx>

    fun transfersAll(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrTransfer>

    fun transfersReceived(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrTransfer>

    fun transfersSend(
        addresses: List<String>,
        contracts: List<String> = emptyList(),
        limit: Int = 1000,
        offset: Int = 0,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ): List<AddrTransfer>

    fun topSendersCount(
        address: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ) : List<SenderSimple>

    fun topReceiversCount(
        address: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ) : List<ReceiverSimple>
}