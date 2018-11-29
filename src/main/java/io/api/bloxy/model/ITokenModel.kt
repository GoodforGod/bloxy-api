package io.api.bloxy.model

import io.api.bloxy.model.dto.TokenType


/**
 * Model have Ethereum ERC token type
 *
 * @see TokenType
 *
 * @author GoodforGod
 * @since 28.11.2018
 */
interface ITokenModel {
    val tokenType: TokenType

    fun isUnknown() : Boolean = tokenType == TokenType.UNKNOWN

    fun isERC20() : Boolean = tokenType == TokenType.ERC20
}