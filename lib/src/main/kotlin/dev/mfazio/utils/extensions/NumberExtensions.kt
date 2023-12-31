package dev.mfazio.utils.extensions

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