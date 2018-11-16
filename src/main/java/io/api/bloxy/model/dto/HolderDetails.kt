package io.api.bloxy.model.dto


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class HolderDetails(val address: String?,
                         val address_type: String?,
                         val to_count: Number?,
                         val uniq_senders: Number?,
                         val from_count: Number?,
                         val uniq_receivers: Number?,
                         val to_amount: Number?,
                         val from_amount: Number?,
                         val first_tx_at: String?,
                         val last_tx_at: String?,
                         val balance: Number?,
                         val annotation: String?) {


}