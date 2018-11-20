package io.api.bloxy.model.dto.moneyflow

import io.api.bloxy.model.IModel


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SenderSimple(
    val sender: String = "",
    val sender_type: String = "",
    val transactions: Long = 0,
    val annotation: String = ""
): IModel {
    override fun isEmpty(): Boolean {
        return sender.isEmpty() && transactions == 0L
    }
}