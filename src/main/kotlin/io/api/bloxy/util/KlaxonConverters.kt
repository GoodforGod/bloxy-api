package io.api.bloxy.util

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import io.api.bloxy.model.dto.dex.Args


/**
 * Klaxon JSON parser extensions
 *
 * @author GoodforGod
 * @since 22.11.2018
 */
internal class KlaxonConverters {
    companion object {
        internal val argsConverter = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Args::class.java

            override fun toJson(value: Any): String = ""

            override fun fromJson(jv: JsonValue) = Args(jv.obj?.map ?: emptyMap())
        }
    }
}