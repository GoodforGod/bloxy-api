package io.api.bloxy.core

import io.api.bloxy.manager.ParamConverter.Companion.MAX_DATETIME
import io.api.bloxy.manager.ParamConverter.Companion.MIN_DATETIME
import io.api.bloxy.model.dto.token.*
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface ITokenApi {

    fun holders(
        contract: String,
        limit: Int = 100
    ): List<Holder>

    fun holderDetails(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        minBalance: Double = 1.0e-6,
        minReceived: Int = 1,
        minSend: Int = 0
    ): List<HolderDetails>

    fun holderCorrelations(
        contracts: List<String>
    ): List<TokenCorrelation>

    fun holderSimilar(
        contracts: String
    ): List<HolderSimilar>

    fun tokenByNameOrSymbol(
        nameOrSymbol: String,
        limit: Int = 100,
        offset: Int = 0
    ): List<Token>

    fun tokenDetails(
        contracts: List<String>
    ): List<TokenDetails>

    fun tokenStatistic(
        contract: String
    ): List<TokenStatistic>

    fun tokenTransfers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<TokenTransfer>
}