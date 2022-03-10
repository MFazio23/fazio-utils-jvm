package dev.mfazio.utils.extensions

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

fun LocalDateTime.isBetween(
    start: LocalDateTime = LocalDateTime.now(),
    end: LocalDateTime = LocalDateTime.now()
): Boolean =
    (this.isAfter(start) || this.isEqual(start)) && (this.isBefore(end) || this.isEqual(end))

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