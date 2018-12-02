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
     *
     */
    fun signaturesByHash(
        hashes: List<String>,
        limit: Int = 100,
        offset: Int = 0
    ) : List<SignatureDetail>

    /**
     *
     */
    fun signaturesByName(
        names: List<String>,
        limit: Int = 100,
        offset: Int = 0
    ) : List<SignatureDetail>

    /**
     *
     */
    fun withMethod(
        signatureHash: String,
        limit: Int = 100,
        offset: Int = 0
    ) : List<ContractDetail>

    /**
     *
     */
    fun withEvent(
        signatureHash: String,
        limit: Int = 100,
        offset: Int = 0
    ) : List<ContractDetail>

    /**
     *
     */
    fun methods(
        contract: String
    ) : List<Method>

    /**
     *
     */
    fun events(
        contract: String
    ) : List<Event>
}