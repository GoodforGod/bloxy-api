package io.api.bloxy.model.dto


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class Correlation(val symbol:String,
                       val address: String,
                       val transfers: Int) {
}