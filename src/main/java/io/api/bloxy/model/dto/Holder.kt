package io.api.bloxy.model.dto

import java.math.BigInteger


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
data class Holder(val address: String = "",
                  val address_type: String = "",
                  val annotation: String = "",
                  val balance: BigInteger = BigInteger.ZERO) {
}