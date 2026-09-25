package dev.mfazio.utils.extensions

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

fun LocalDateTime.isBetween(
    start: LocalDateTime? = null,
    end: LocalDateTime? = null
): Boolean {
    val now = LocalDateTime.now()
    return this in (start ?: now)..(end ?: now)
}

fun LocalDate.getOrdinalSuffix() = this.dayOfMonth.let { day ->
    if (day in 4..20) "th"
    else when (day % 10) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> "th"
    }
}

fun LocalDateTime.getOrdinalSuffix() = this.toLocalDate().getOrdinalSuffix()
fun OffsetDateTime.getOrdinalSuffix() = this.toLocalDate().getOrdinalSuffix()
fun ZonedDateTime.getOrdinalSuffix() = this.toLocalDate().getOrdinalSuffix()