package io.api.bloxy.core

import io.api.bloxy.model.dto.Correlation
import io.api.bloxy.model.dto.Holder
import io.api.bloxy.model.dto.HolderDetails


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
interface ITokenApi {

    fun holders(address: String,
                   limit: Int = 100) : List<Holder>

    fun holderDetails(address: String,
                         limit: Int = 100,
                         balanceMin: Double = 1.0e-6,
                         toCountMin: Int = 1,
                         fromCountMin: Int = 0,
                         offset:Int = 0) : List<HolderDetails>

    fun holderCorrelations(addresses: List<String>) : List<Correlation>

    fun honderSimilars(address: String) : List<String>

}