package io.api.bloxy.model.dto.dex


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
enum class MethodType {
    WITHDRAW_TOKEN,
    WITHDRAW,
    TRADE,
    CANCEL_ORDER,
    DEPOSIT,
    DEPOSIT_TOKEN,
    UNKNOWN;

    companion object {
        fun parse(value: String): MethodType {
            return when (value) {
                "cancelOrder" -> CANCEL_ORDER
                "withdrawToken" -> WITHDRAW_TOKEN
                "depositToken" -> DEPOSIT_TOKEN
                "withdraw" -> WITHDRAW
                "deposit" -> DEPOSIT
                "trade" -> TRADE
                else -> UNKNOWN
            }
        }
    }
}