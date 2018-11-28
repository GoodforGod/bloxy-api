package io.api.bloxy.model.dto


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
enum class DangerLevel {
    SUCCESS,
    WARNING,
    DANGER,
    UNKNOWN;

    companion object {
        fun parse(value:String) : DangerLevel {
            return when(value) {
                "success" -> SUCCESS
                "warning" -> WARNING
                "danger" -> DANGER
                else -> UNKNOWN
            }
        }
    }
}