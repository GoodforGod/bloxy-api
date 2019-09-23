package io.api.bloxy.model


/**
 * Valid model funcs
 *
 * @author GoodforGod
 * @since 21.11.2018
 */
interface IValidModel : IModel {
    fun isValid(): Boolean
}