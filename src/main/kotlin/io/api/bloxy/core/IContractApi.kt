package io.api.bloxy.core

import io.api.bloxy.model.dto.contract.ContractDetail
import io.api.bloxy.model.dto.contract.Event
import io.api.bloxy.model.dto.contract.Method
import io.api.bloxy.model.dto.contract.SignatureDetail


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
     * List of smart contract methods and call statistics
     * @param contract to look for
     */
    fun methods(
        contract: String
    ): List<Method>

    /**
     * List of smart contract methods and call statistics
     * @param contract to look for
     */
    fun events(
        contract: String
    ): List<Event>
}