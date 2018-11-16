package io.api.bloxy.core.impl

import io.api.bloxy.core.ITokenApi
import io.api.bloxy.model.dto.token.*
import java.time.LocalDateTime


/**
 * ! NO DESCRIPTION !
 *
 * @see ITokenApi
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class TokenApiProvider : ITokenApi {

    override fun holders(address: String, limit: Int): List<Holder> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun holderDetails(
        address: String,
        limit: Int,
        minBalance: Double,
        toCountMin: Int,
        fromCountMin: Int
    ): List<HolderDetails> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun holderCorrelations(addresses: List<String>): List<TokenCorrelation> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun holderSimilars(address: String): List<HolderSimilar> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun tokenByNameOrSymbol(nameOrSymbol: String): List<Token> {
        TODO("not implemented yet") //File | Settings | File Templates
    }

    override fun tokenTransfers(
        address: String,
        limit: Int,
        fromTime: LocalDateTime,
        tillTime: LocalDateTime
    ): List<TokenTransfer> {
        TODO("not implemented yet") //File | Settings | File Templates
    }
}