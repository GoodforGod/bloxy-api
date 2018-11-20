package io.api.bloxy.model.dto.dex

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class DexTrade(
    val tx_hash: String = "",
    val tx_time: String = "",
    val tx_date: String = "",
    val tx_sender: String = "",
    val smart_contract_id: Long = 0,
    val smart_contract_address: String = "",
    val contract_type: String = "",
    val maker: String = "",
    val taker: String = "",
    val amountBuy: Double = .0,
    val makerFee: Double = .0,
    val buyCurrencyId: Long = 0,
    val buySymbol: String = "",
    val amountSell: Double = .0,
    val takerFee: Double = .0,
    val sellCurrencyId: Long = 0,
    val sellSymbol: String = "",
    val maker_annotation: String = "",
    val taker_annotation: String = "",
    val protocol: String = "",
    val buyAddress: String = "",
    val sellAddress: String = ""
) : IModel {
    override fun isEmpty(): Boolean {
        return smart_contract_address.isEmpty() && protocol.isEmpty()
    }
}