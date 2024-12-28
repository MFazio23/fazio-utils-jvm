package dev.mfazio.utils.math

import kotlin.math.max

fun findLCM(a: Int, b: Int): Int = findLCM(a.toLong(), b.toLong()).toInt()

fun findLCM(a: Long, b: Long): Long {
    val maxInput = max(a, b)
    val maxLCM = a * b

    var lcm = maxInput

    while (lcm < maxLCM) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += maxInput
    }

    return maxLCM
}

fun Collection<Int>.findLCM() = this.fold(1) { lcm, num -> findLCM(lcm, num) }

fun Collection<Long>.findLCM() = this.fold(1L) { lcm, num -> findLCM(lcm, num) }