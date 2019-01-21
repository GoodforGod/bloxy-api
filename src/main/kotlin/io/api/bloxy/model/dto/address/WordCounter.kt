package io.api.bloxy.model.dto.address

import io.api.bloxy.model.IModel

/**
 * "default comment"
 *
 * @author GoodforGod
 * @since 10.01.2019
 */
data class WordCounter(
    val word: String = "",
    val address: String = "",
    val count: Int = 0
) : IModel {
    override fun isEmpty(): Boolean = word.isEmpty()
}