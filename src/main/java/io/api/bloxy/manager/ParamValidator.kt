package io.api.bloxy.manager

import io.api.bloxy.error.ParamException


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
open class ParamValidator {

    private fun isAddressValid(address: String): Boolean {
        return address.isNotEmpty() && address.matches("0x[a-zA-Z0-9]{40}".toRegex())
    }

    private fun isTxHashValid(address: String): Boolean {
        return address.isNotEmpty() && address.matches("0x[a-zA-Z0-9]{60}".toRegex())
    }

    fun checkNonBlank(param: String): String {
        return if (param.isNotBlank()) param else throw ParamException("Param is blank : $param")
    }

    fun checkNonBlank(params: List<String>): List<String> {
        params.forEach { a -> if (a.isBlank()) throw ParamException("Param is blank : $a") }
        return params
    }

    fun checkAddress(address: String): String {
        return if (isAddressValid(address)) address else throw ParamException("Address is not Ethereum format : $address")
    }

    fun checkAddress(addresses: List<String>): List<String> {
        addresses.forEach { a -> if (!isAddressValid(a)) throw ParamException("Address is not Ethereum format : $a") }
        return addresses
    }

    fun checkTxs(tx: String): String {
        return if (isTxHashValid(tx)) tx else throw ParamException("TxHash is not Ethereum format : $tx")
    }

    fun checkTxs(txs: List<String>): List<String> {
        txs.forEach { t -> if (!isTxHashValid(t)) throw ParamException("TxHash is not Ethereum format : $t") }
        return txs
    }
}