package io.api.bloxy.util

import io.api.bloxy.error.ParamException
import io.api.bloxy.model.IValidModel
import java.util.stream.Collectors


/**
 * API provider extension to validate parameters
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class ParamValidator {

    fun isAddressValid(address: String): Boolean {
        return address.isNotEmpty() && address.matches("0x[a-zA-Z0-9]{40}".toRegex())
    }

    fun isTxHashValid(address: String): Boolean {
        return address.isNotEmpty() && address.matches("0x[a-zA-Z0-9]{64}".toRegex())
    }

    fun <T : IValidModel> validOnly(models: List<T>): List<T> {
        return models.stream().filter { m -> m.isValid() }.collect(Collectors.toList())
    }

    fun checkNonBlank(param: String): String {
        return if (param.isNotBlank()) param else throw ParamException("Param is blank : $param")
    }

    fun checkNonBlank(params: List<String>): List<String> {
        if (params.isNullOrEmpty()) throw ParamException("Params is null or empty")
        params.forEach { p -> checkNonBlank(p) }
        return params
    }

    fun checkAddrRequired(address: String): String {
        return if (isAddressValid(address)) address else throw ParamException("Address is not Ethereum format : $address")
    }

    fun checkAddrRequired(addresses: List<String>): List<String> {
        if (addresses.isNullOrEmpty()) throw ParamException("Addresses are null or empty")
        return checkAddr(addresses)
    }

    fun checkAddr(addresses: List<String>): List<String> {
        addresses.forEach { a -> checkAddrRequired(a) }
        return addresses
    }

    fun checkAddr(address: String): String {
        if(address.isEmpty())
            return address

        return if (isAddressValid(address)) address else throw ParamException("Address is not Ethereum format : $address")
    }

    fun checkTxRequired(tx: String): String {
        if (tx.isEmpty()) throw ParamException("TxHash is null or empty")
        return if (isTxHashValid(tx)) tx else throw ParamException("TxHash is not Ethereum format : $tx")
    }

    fun checkTxRequired(txs: List<String>): List<String> {
        if (txs.isNullOrEmpty()) throw ParamException("TxHashs are null or empty")
        txs.forEach { tx -> if (!isTxHashValid(tx)) throw ParamException("TxHash is not Ethereum format : $tx")}
        return txs
    }
}