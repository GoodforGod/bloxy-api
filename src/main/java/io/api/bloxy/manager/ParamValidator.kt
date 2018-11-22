package io.api.bloxy.manager

import io.api.bloxy.error.ParamException
import io.api.bloxy.model.IValidModel
import java.util.stream.Collectors


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class ParamValidator {

    fun isAddressValid(address: String): Boolean {
        return address.isNotEmpty() && address.matches("0x[a-zA-Z0-9]{40}".toRegex())
    }

    fun isTxHashValid(address: String): Boolean {
        return address.isNotEmpty() && address.matches("0x[a-zA-Z0-9]{60}".toRegex())
    }

    fun <T : IValidModel> validOnly(models: List<T>) : List<T> {
        return models.stream().filter{m -> m.isValid()}.collect(Collectors.toList())
    }

    fun checkNonBlank(param: String): String {
        return if (param.isNotBlank()) param else throw ParamException("Param is blank : $param")
    }

    fun checkNonBlank(params: List<String>): List<String> {
        if(params.isNullOrEmpty()) throw ParamException("Params is null or empty")

        params.forEach { a -> if (a.isBlank()) throw ParamException("Param is blank : $a") }
        return params
    }

    fun checkAddressRequired(address: String): String {
        return if (isAddressValid(address)) address else throw ParamException("Address is not Ethereum format : $address")
    }

    fun checkAddressRequired(addresses: List<String>): List<String> {
        if(addresses.isNullOrEmpty()) throw ParamException("Addresses is null or empty")

        addresses.forEach { a -> if (!isAddressValid(a)) throw ParamException("Address is not Ethereum format : $a") }
        return addresses
    }

    fun checkAddress(addresses: List<String>): List<String> {
        addresses.forEach { a -> if (!isAddressValid(a)) throw ParamException("Address is not Ethereum format : $a") }
        return addresses
    }

    fun checkTxsRequired(tx: String): String {
        return if (isTxHashValid(tx)) tx else throw ParamException("TxHash is not Ethereum format : $tx")
    }

    fun checkTxsRequired(txs: List<String>): List<String> {
        if(txs.isNullOrEmpty()) throw ParamException("TxHashs is null or empty")

        txs.forEach { t -> if (!isTxHashValid(t)) throw ParamException("TxHash is not Ethereum format : $t") }
        return txs
    }

    fun checkTxs(txs: List<String>): List<String> {
        txs.forEach { t -> if (!isTxHashValid(t)) throw ParamException("TxHash is not Ethereum format : $t") }
        return txs
    }
}