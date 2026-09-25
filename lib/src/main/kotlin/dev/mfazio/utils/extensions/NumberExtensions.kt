package dev.mfazio.utils.extensions

import kotlin.math.abs
import kotlin.math.round

fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = this % 2 != 0

fun Int.safeDivide(divisor: Int): Double =
    this.toDouble().safeDivide(divisor.toDouble())

fun Float.safeDivide(divisor: Float): Double =
    this.toDouble().safeDivide(divisor.toDouble())

fun Double.safeDivide(divisor: Double): Double =
    if (divisor == 0.0) 0.0 else (this / divisor)

fun Double.toTwoDigits() = "%.2f".format(this)

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Float?.orZero() = this ?: 0.0F

fun Double.round(places: Int): Double {
    var multiplier = 1.0
    repeat(places) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun Double.roundToText(places: Int, padToLength: Boolean = true, removeLeadingZero: Boolean = false): String {
    var text = this.round(places).toString()

    if (padToLength) {
        // Add to the length for the integer(s) and decimal point.
        text = text.padEnd(places + text.substringBefore(".").length + 1, '0')
    }

    if (removeLeadingZero && text.take(1) == "0") {
        text = text.drop(1)
    }

    return text
}

fun Int.withOrdinal() = this.toString() + when {
    abs(this % 100) in 11..13 -> "th"
    abs(this % 10) == 1 -> "st"
    abs(this % 10) == 2 -> "nd"
    abs(this % 10) == 3 -> "rd"
    else -> "th"
}