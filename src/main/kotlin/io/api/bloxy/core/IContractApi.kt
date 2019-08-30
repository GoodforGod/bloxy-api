package io.api.bloxy.core

import io.api.bloxy.model.dto.contract.*
import io.api.bloxy.model.dto.contract.ContractStat.Aggregator
import io.api.bloxy.util.ParamConverter.Companion.MAX_DATE
import io.api.bloxy.util.ParamConverter.Companion.MIN_DATE
import java.time.LocalDate


/**
 * API for smart contracts analytics and parsing
 * More information - https://bloxy.info/api_methods#smart_contract
 *
 * @author GoodforGod
 * @since 02.12.2018
 */
internal interface IContractApi {

    /**
     * List all methods/events with the specified signature hash(es)
     * Bloxy - Find methods/events by signature
     * @param hashes
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    fun signaturesByHash(
        hashes: List<String>,
        limit: Int = 100,
        offset: Int = 0
    ): List<SignatureDetail>

    /**
     * List all methods/events with the specified name(s)
     * Bloxy - Find methods/events by name
     * @param names of event or method
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    fun signaturesByName(
        names: List<String>,
        limit: Int = 100,
        offset: Int = 0
    ): List<SignatureDetail>

    /**
     * List of smart contracts where the method used
     * Bloxy - Contract Methods
     * @param signatureHash
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    fun withMethod(
        signatureHash: String,
        limit: Int = 100,
        offset: Int = 0
    ): List<ContractDetail>

    /**
     * List of smart contracts where the event used
     * Bloxy - Contract Events
     * @param signatureHash
     * @param limit max result (MAX 101000)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    fun withEvent(
        signatureHash: String,
        limit: Int = 100,
        offset: Int = 0
    ): List<ContractDetail>

    /**
     * Daily/Monthly/Yearly/All statistical metrics for a list of smart contracts
     * Bloxy - Contract Statistics
     * @param contract
     * @param aggregator (default all)
     * @param since timestamp
     * @param till timestamp
     */
    fun statistic(
        contract: String,
        aggregator: Aggregator = Aggregator.ALL,
        since: LocalDate = MIN_DATE,
        till: LocalDate = MAX_DATE
    ) : List<ContractStat>

    /**
     * List of smart contract methods and call statistics
     * Bloxy - Contracts with Method
     * @param contract to look for
     */
    fun methods(
        contract: String
    ): List<Method>

    /**
     * List of smart contract methods and call statistics
     * Bloxy - Contracts with Event
     * @param contract to look for
     */
    fun events(
        contract: String
    ): List<Event>
}