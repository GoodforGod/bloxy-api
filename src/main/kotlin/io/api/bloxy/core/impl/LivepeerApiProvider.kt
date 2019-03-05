package io.api.bloxy.core.impl

import io.api.bloxy.executor.IHttpClient
import io.api.bloxy.model.dto.livepeer.*
import org.jetbrains.annotations.NotNull


/**
 * API for Livepeer contract monitoring
 * More information - https://bloxy.info/api_methods#livepeer
 *
 * @see io.api.bloxy.core.ILivepeerApi
 * @see io.api.bloxy.core.impl.BasicProvider
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
class LivepeerApiProvider internal constructor(client: IHttpClient, key: String) : BasicProvider(client, "livepeer", key) {

    private fun managerAsParam(managerProxy: String) : String {
        return if(managerProxy.isEmpty()) "" else "&manager_proxy_address=$managerProxy"
    }

    private fun managerRoundAsParam(managerProxy: String) : String {
        return if(managerProxy.isEmpty()) "" else "&round_manager_proxy_address=$managerProxy"
    }

    /**
     * @see io.api.bloxy.core.ILivepeerApi.bonds
     */
    @NotNull
    @JvmOverloads
    fun bonds(
        address: String,
        managerProxy: String = "",
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Bond> {
        val managersParam = "${managerAsParam(managerProxy)}${managerRoundAsParam(roundManagerProxy)}"
        val params = "bonds?address=$address$managersParam"
        return getOffset(params, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.ILivepeerApi.unbonds
     */
    @NotNull
    @JvmOverloads
    fun unbonds(
        address: String,
        managerProxy: String = "",
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Bond> {
        val managersParam = "${managerAsParam(managerProxy)}${managerRoundAsParam(roundManagerProxy)}"
        val params = "unbonds?address=$address$managersParam"
        return getOffset(params, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.ILivepeerApi.rewards
     */
    @NotNull
    @JvmOverloads
    fun rewards(
        address: String,
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Reward> {
        val params = "rewards?address=$address$${managerRoundAsParam(roundManagerProxy)}"
        return getOffset(params, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.ILivepeerApi.rounds
     */
    @NotNull
    @JvmOverloads
    fun rounds(
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Round> {
        val params = "rounds?${managerRoundAsParam(roundManagerProxy)}"
        return getOffset(params, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.ILivepeerApi.events
     */
    @NotNull
    @JvmOverloads
    fun events(
        delegate: String = "",
        delegator: String = "",
        managerProxy: String = "",
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Event> {
        val delegateParam = if(delegate.isEmpty()) "" else "&delegate=$delegate"
        val delegatorParam = if(delegator.isEmpty()) "" else "&delegator=$delegator"
        val managersParam = "${managerAsParam(managerProxy)}${managerRoundAsParam(roundManagerProxy)}"
        val params = "events?$delegateParam$delegatorParam$managersParam"
        return getOffset(params, limit, offset)
    }

    /**
     * @see io.api.bloxy.core.ILivepeerApi.delegates
     */
    @NotNull
    @JvmOverloads
    fun delegates(
        managerProxy: String = ""
    ): List<Delegate> {
        val params = "delegates?${managerAsParam(managerProxy)}"
        return get(params)
    }

    /**
     * @see io.api.bloxy.core.ILivepeerApi.delegators
     */
    @NotNull
    @JvmOverloads
    fun delegators(
        managerProxy: String = ""
    ): List<Delegator> {
        val params = "delegators?${managerAsParam(managerProxy)}"
        return get(params)
    }

    /**
     * @see io.api.bloxy.core.ILivepeerApi.delegatorStatus
     */
    @NotNull
    @JvmOverloads
    fun delegatorStatus(
        managerProxy: String = "",
        roundManagerProxy: String = ""
    ): List<DelegatorStatus> {
        val params = "status?${managerAsParam(managerProxy)}${managerRoundAsParam(roundManagerProxy)}"
        return get(params)
    }
}