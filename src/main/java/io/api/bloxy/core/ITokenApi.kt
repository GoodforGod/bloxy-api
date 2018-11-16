package io.api.bloxy.core

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
        address: String,
        limit: Int = 100
    ): List<Holder>

    // NO OFFSET, JUST DO NEEDED AMOUNT OF CYCLES WITH LIMIT TILL RETURN EMPTY
    fun holderDetails(
        address: String,
        limit: Int = 100,
        minBalance: Double = 1.0e-6,
        toCountMin: Int = 1,
        fromCountMin: Int = 0
    ): List<HolderDetails>

    fun holderCorrelations(
        addresses: List<String>
    ): List<TokenCorrelation>

    fun holderSimilars(
        address: String
    ): List<HolderSimilar>

    fun tokenByNameOrSymbol(
        nameOrSymbol: String
    ): List<Token>

    // NO OFFSET, JUST DO NEEDED AMOUNT OF CYCLES WITH LIMIT TILL RETURN EMPTY
    fun tokenTransfers(
        address: String,
        limit: Int = 100,
        fromTime: LocalDateTime = LocalDateTime.MIN,
        tillTime: LocalDateTime = LocalDateTime.MAX
    ): List<TokenTransfer>
}