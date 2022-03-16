package dev.mfazio.utils.random

import kotlin.random.Random

open class BaseRandom : Random() {
    override fun nextBits(bitCount: Int): Int = bitCount
}

class DoubleListRandom(val results: List<Double>) : BaseRandom() {
    override fun nextDouble(): Double {
        return super.nextDouble()
    }

    override fun nextDouble(until: Double): Double {
        return super.nextDouble(until)
    }

    override fun nextDouble(from: Double, until: Double): Double {
        return super.nextDouble(from, until)
    }
}