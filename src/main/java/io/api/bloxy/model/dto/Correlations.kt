package io.api.bloxy.model.dto


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.11.2018
 */
class Correlations(val symbols:List<String>) {

    private val correlations: Map<String, Pair<List<String>, Int>> = HashMap()
}