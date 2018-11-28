package io.api.bloxy.model

import io.api.bloxy.model.dto.TokenType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.11.2018
 */
interface ITokenModel {
    val tokenType: TokenType

    fun isERC20() : Boolean = tokenType == TokenType.ERC20
}