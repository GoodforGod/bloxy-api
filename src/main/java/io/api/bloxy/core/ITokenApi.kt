package io.api.bloxy.core

import io.api.bloxy.model.dto.token.*
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATETIME
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATETIME
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface ITokenApi {

    @NotNull
    fun holders(
        contract: String,
        limit: Int = 100
    ): List<Holder>

    @NotNull
    fun holderDetails(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        minBalance: Double = 1.0e-6,
        minReceived: Int = 1,
        minSend: Int = 0
    ): List<HolderDetails>

    @NotNull
    fun holderCorrelations(
        contracts: List<String>
    ): List<TokenCorrelation>

    @NotNull
    fun holderSimilar(
        contracts: String
    ): List<HolderSimilar>

    @NotNull
    fun tokenByNameOrSymbol(
        nameOrSymbol: String,
        limit: Int = 100
    ): List<Token>

    @NotNull
    fun tokenDetails(
        contracts: List<String>
    ): List<TokenDetails>

    @NotNull
    fun tokenStatistic(
        contract: String
    ): List<TokenStatistic>

    @NotNull
    fun tokenTransfers(
        contract: String,
        limit: Int = 100,
        offset: Int = 0,
        since: LocalDateTime = MIN_DATETIME,
        till: LocalDateTime = MAX_DATETIME
    ): List<TokenTransfer>
}