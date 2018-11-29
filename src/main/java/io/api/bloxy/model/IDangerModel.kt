package io.api.bloxy.model

import io.api.bloxy.model.dto.DangerLevel


/**
 * Model have danger level
 *
 * @see DangerLevel
 *
 * @author GoodforGod
 * @since 28.11.2018
 */
interface IDangerModel {
    val level: DangerLevel
}