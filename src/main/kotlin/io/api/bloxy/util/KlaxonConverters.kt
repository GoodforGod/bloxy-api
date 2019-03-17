package io.api.bloxy.util

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import io.api.bloxy.model.dto.dex.Args
import java.math.BigDecimal


/**
 * Klaxon JSON parser extensions
 *
 * @author GoodforGod
 * @since 22.11.2018
 */
//@Target(AnnotationTarget.FIELD)
//internal annotation class KlaxonBigDecimal

@Target(AnnotationTarget.FIELD)
internal annotation class KlaxonArgs

@Target(AnnotationTarget.FIELD)
internal annotation class KlaxonStr

@Target(AnnotationTarget.FIELD)
internal annotation class KlaxonDouble

@Target(AnnotationTarget.FIELD)
internal annotation class KlaxonDecimal

internal class KlaxonConverters {
    companion object {
        internal val argsConverter = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Args::class.java

            override fun toJson(value: Any): String = ""

            override fun fromJson(jv: JsonValue): Args {
                return try {
                    Args(jv.obj?.map ?: emptyMap())
                } catch (e: Exception) {
                    Args(emptyMap())
                }
            }
        }

        internal val decimalConverter = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == BigDecimal::class.java

            override fun toJson(value: Any): String = value.toString()

            override fun fromJson(jv: JsonValue): BigDecimal {
                return when {
                    jv.bigDecimal != null -> jv.bigDecimal!!
                    jv.double != null -> BigDecimal.valueOf(jv.double!!)
                    else -> BigDecimal.ZERO
                }
            }
        }

        internal val nonNullStrConverter = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == String::class.java

            override fun toJson(value: Any): String = value.toString()

            override fun fromJson(jv: JsonValue): String = jv.string ?: ""
        }

        internal val nonNullDoubleConverter = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Double::class.java

            override fun toJson(value: Any): String = value.toString()

            override fun fromJson(jv: JsonValue): Double = jv.double ?: .0
        }

//        internal val bigDecimalConverter = object : Converter {
//            override fun canConvert(cls: Class<*>) = cls == BigDecimal::class.java
//
//            override fun toJson(value: Any): String = ""
//
//            override fun fromJson(jv: JsonValue) : BigDecimal {
//                print("kek")
//                return BigDecimal("1")
//            }
//        }
    }
}